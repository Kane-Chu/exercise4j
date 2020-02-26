package kane.exercise4j.springbootgraphql.resolver;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLResolver;
import kane.exercise4j.springbootgraphql.entity.Author;
import kane.exercise4j.springbootgraphql.entity.Book;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

/**
 * 自定义resolver
 *
 * @author kane
 */
@Component
public class AuthorResolver implements GraphQLResolver<Author> {
    public List<Book> getBooks(Author author) {
        return Lists.newArrayList();
    }
}