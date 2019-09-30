package kane.exercise.springspel.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @author kane
 */
@Data
@Builder
public class User {
    private String uid;
    private String name;
}