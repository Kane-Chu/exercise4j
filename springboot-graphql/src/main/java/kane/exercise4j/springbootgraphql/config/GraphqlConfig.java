package kane.exercise4j.springbootgraphql.config;

import com.coxautodev.graphql.tools.SchemaParser;
import com.oembedler.moon.graphql.boot.GraphQLJavaToolsAutoConfiguration;
import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.ExecutionStrategy;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLServlet;
import graphql.servlet.SimpleGraphQLServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kane
 */
@Configuration
public class GraphqlConfig {

//    @Value("${graphql.servlet.mapping:/books}")
//    private String graphqlServletMapping;
//
//    @Bean
//    public GraphQLSchema schema(SchemaParser schemaParser) {
//        return schemaParser.parseSchemaObjects().toSchema();
//    }
//
//    @Bean
//    public ServletRegistrationBean<GraphQLServlet> servletRegistrationBean(SchemaParser schemaParser) {
//        ExecutionStrategy executionStrategy = new AsyncExecutionStrategy();
//        GraphQLServlet servlet = new SimpleGraphQLServlet(schema(schemaParser), executionStrategy);
//        return new ServletRegistrationBean<>(servlet, graphqlServletMapping);
//    }
}