package kane.exercise.commons.test.base;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.URIParameter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.yaml.snakeyaml.util.UriEncoder;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * @author kane
 */
@Slf4j
public class Test1 {
    @Test
    public void test1() {
        LocalDate lastest = LocalDate.parse("20191208", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate current = LocalDate.parse("20190901", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(lastest.getYear() + "-" + lastest.getMonth());
        System.out.println(current.getYear() + "-" + current.getMonth());
        System.out.println((lastest.getYear() * 12 + lastest.getMonthValue() - current.getYear() * 12 - current.getMonthValue()));
    }

    @Test
    public void test2() {
        System.out.println(String.format("%d %s", 1, "sadasd"));
    }

    @Test
    public void test3() {
        //noinspection UnstableApiUsage
        Map<String, String> split =
                Splitter.on(';').trimResults().withKeyValueSeparator(Splitter.on(",").trimResults()).split("LIST:0 ,男 ; 1,0".substring(5));

    }

    @Test
    public void uriTest() {
        URI uri = URI.create("ftp://chujm:passs%20@127.0.0.1/home/kane");
        URI uri2 = URI.create("file:///home/kane");
        System.out.println(uri.getScheme());
        System.out.println(uri.getUserInfo());
        System.out.println(uri.getRawUserInfo());
        System.out.println(uri.getHost());
        System.out.println(uri.getPort());
        System.out.println(uri.getPath());
        System.out.println(uri2.getScheme());
        System.out.println(uri2.getPath());
    }

    @Test
    public void pathTest(){
        String unixPath = "/a/b/c.txt";
        String winPath = "E:\\a\\b\\c.txt";
        System.out.println(Paths.get(winPath).getFileSystem().toString());
        System.out.println(Paths.get(unixPath).getFileName().toString());
        System.out.println(Paths.get(winPath).getFileName().toString());

    }

    @Test
    public void test4(){
        LocalDateTime start = LocalDateTime.parse("20200514095359", DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        System.out.println(start.until(LocalDateTime.now(), DAYS));
    }


}