package by.feedblog.post.data;

import by.feedblog.post.domain.Like;
import by.feedblog.post.domain.Post;

import java.util.List;

public interface LikeData {
    Like save(Like like);
    boolean existsByPost(Post post);
    List<Like> findAllByPost(Post post);
    void deleteByPost(Post post);
}
