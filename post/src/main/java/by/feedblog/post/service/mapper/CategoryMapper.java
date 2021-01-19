package by.feedblog.post.service.mapper;

import by.feedblog.post.domain.Category;
import by.feedblog.post.service.dto.CategoryDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {

    public Category dtoToDomain(CategoryDto categoryDto) {
        return getCategory(categoryDto);
    }

    public CategoryDto domainToDto(Category category) {
        return getCategoryDto(category);
    }

    public List<Category> dtoListToDomainList(List<CategoryDto> categoryDtoList) {
        List<Category> categories = new ArrayList<>();
        for (CategoryDto categoryDto : categoryDtoList) {
            Category category = getCategory(categoryDto);
            categories.add(category);
        }
        return categories;
    }

    public List<CategoryDto> domainListToDtoList(List<Category> categories) {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category category : categories) {
            CategoryDto categoryDto = getCategoryDto(category);
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    private Category getCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        return category;
    }

    private CategoryDto getCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}
