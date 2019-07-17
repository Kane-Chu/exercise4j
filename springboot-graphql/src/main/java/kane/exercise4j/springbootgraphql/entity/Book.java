package kane.exercise4j.springbootgraphql.entity;

import lombok.Data;

/**
 * @author kane
 */
@Data
public class Book {
    private Integer id;
    private String name;
    private Author author;
    private String publisher;
}