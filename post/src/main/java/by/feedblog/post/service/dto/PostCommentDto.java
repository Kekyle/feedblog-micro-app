package by.feedblog.post.service.dto;

import by.feedblog.post.domain.Comment;
import by.feedblog.post.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentDto {
    private long id;
    private Comment comment;
    private User user;
}
