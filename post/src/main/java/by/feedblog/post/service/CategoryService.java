package by.feedblog.post.service;

import by.feedblog.post.data.CategoryData;
import by.feedblog.post.domain.Category;
import by.feedblog.post.service.dto.CategoryDto;
import by.feedblog.post.service.exception.CategoryIsAlreadyExistException;
import by.feedblog.post.service.exception.CategoryNotFoundException;
import by.feedblog.post.service.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryData categoryData;

    @Autowired
    private CategoryMapper categoryMapper;

    public Category save(CategoryDto category) {
        if (categoryData.existsByName(category.getName())) {
            throw new CategoryIsAlreadyExistException("Category is already exist");
        } else {
            Category currentCategory = categoryMapper.dtoToDomain(category);
            return categoryData.save(currentCategory);
        }
    }

    public Category update(CategoryDto category) {
        if (categoryData.existsById(category.getId())) {
            Category currentCategory = categoryMapper.dtoToDomain(category);
            return categoryData.save(currentCategory);
        }
        throw new CategoryNotFoundException("Category not found");
    }

    public Category findById(long id) {
        if (categoryData.existsById(id)) {
            return categoryData.findById(id);
        }
        throw new CategoryNotFoundException("Category not found");
    }

    public Category findByName(String name) {
        if (categoryData.existsByName(name)) {
            return categoryData.findByName(name);
        }
        throw new CategoryNotFoundException("Category not found");
    }

    public List<Category> findAll() {
        return categoryData.findAll();
    }

    public boolean existsByName(String name){
        return categoryData.existsByName(name);
    }

    public void deleteById(long id) {
        if (categoryData.existsById(id)) {
            categoryData.deleteById(id);
        } else {
            throw new CategoryNotFoundException("Category not found");
        }
    }

    public void deleteByName(String name) {
        if (categoryData.existsByName(name)) {
            categoryData.deleteByName(name);
        } else {
            throw new CategoryNotFoundException("Category not found");
        }
    }
}
