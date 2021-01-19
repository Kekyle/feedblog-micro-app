package by.feedblog.post.data;

import by.feedblog.post.domain.Category;

import java.util.List;

public interface CategoryData {
    Category save(Category category);
    boolean existsByName(String name);
    boolean existsById(long id);
    Category findById(long id);
    Category findByName(String name);
    List<Category> findAll();
    void deleteById(long id);
    void deleteByName(String name);
}
