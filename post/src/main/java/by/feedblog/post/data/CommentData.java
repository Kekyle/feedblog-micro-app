package by.feedblog.post.data;

import by.feedblog.post.domain.Comment;

import java.util.List;

public interface CommentData {
    Comment save(Comment comment);
    boolean existsById(long id);
    boolean existsByComment(String comment);
    Comment findById(long id);
    Comment findByComment(String comment);
    List<Comment> findAll();
    void deleteById(long id);
    void deleteByComment(String comment);
}
