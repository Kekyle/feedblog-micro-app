package by.feedblog.post.data;

import by.feedblog.post.domain.Tag;

import java.util.List;

public interface TagData {
    Tag save(Tag tag);
    boolean existsById(long id);
    boolean existsByName(String name);
    Tag findById(long id);
    Tag findByName(String name);
    List<Tag> findAll();
    void deleteById(long id);
    void deleteByName(String name);
}
