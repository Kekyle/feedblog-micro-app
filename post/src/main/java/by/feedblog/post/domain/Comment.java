package by.feedblog.post.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    private long id;
    private String comment;
    private Post post;

    public Comment(String comment) {
        this.comment = comment;
    }
}
