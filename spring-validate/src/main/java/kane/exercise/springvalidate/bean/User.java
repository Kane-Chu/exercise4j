package kane.exercise.springvalidate.bean;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

/**
 * @author kane
 */
@Data
@Builder
public class User {
    @NotBlank(message = "uid 不能为空")
    private String uid;
    private String name;
}