package kane.exercise4j.springbootgraphql.resolver;

import java.util.ArrayList;
import java.util.List;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import kane.exercise4j.springbootgraphql.entity.Author;
import kane.exercise4j.springbootgraphql.entity.Book;
import org.springframework.stereotype.Component;

/**
 * @author kane
 */
@Component
public class BookQueryResolver implements GraphQLQueryResolver {

    public List<Book> findBooks() {
        Author author = new Author();
        author.setId(1);
        author.setName("Bill Venners");
        author.setAge(40);
        Book book = new Book();
        book.setId(1);
        book.setName("scala编程第三版");
        book.setAuthor(author);
        book.setPublisher("电子工业出版社");
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        return bookList;
    }
}