package by.feedblog.post.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Like {

    private long id;
    private Post post;
    private User user;

    public Like(Post post, User user) {
        this.post = post;
        this.user = user;
    }
}
