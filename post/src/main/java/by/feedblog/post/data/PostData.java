package by.feedblog.post.data;

import by.feedblog.post.domain.Category;
import by.feedblog.post.domain.Post;
import by.feedblog.post.domain.Tag;

import java.util.List;

public interface PostData {
    Post save(Post post);
    Post getOne(long id);
    Post findById(long id);
    boolean existsById(long id);
    List<Post> findAll();
    List<Post> findAllByApproved(boolean isApproved);
    List<Post> findAllByCategory(Category category);
    List<Post> findAllByTags(Tag tag);
    boolean existsByTitle(String title);
    Post findByTitle(String title);
    boolean existsByDescription(String description);
    Post findByDescription(String description);
    void deleteById(long id);
    void deleteByTitle(String title);
    void deleteByDescription(String description);
}
