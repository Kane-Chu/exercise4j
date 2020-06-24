package kane.exercise.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * http server
 * 启动后可使用postman 发送post请求到 http://127.0.0.1:12345
 *
 * @author kane
 */
@Slf4j
public class HttpServer {
    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch)
                            throws Exception {
                        log.info("initChannel ch:{}", ch);
                        ch.pipeline()
                                // 解码request
                                .addLast("decoder", new HttpRequestDecoder())
                                // 编码response
                                .addLast("encoder", new HttpResponseEncoder())
                                // 消息聚合器。如果不使用aggregator，一个http请求就会通过多个Channel被处理，这对我们的业务开发是不方便的
                                .addLast("aggregator", new HttpObjectAggregator(512 * 1024))
                                .addLast("handler", new HttpHandler());
                    }
                })
                // determining the number of connections queued
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);
        ChannelFuture sync = bootstrap.bind(port).sync();
        sync.channel().closeFuture().sync();
    }

    public static void main(String[] args) throws Exception {
        new HttpServer(12345).start();
    }
}