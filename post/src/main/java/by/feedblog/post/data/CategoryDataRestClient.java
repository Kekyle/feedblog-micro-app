package by.feedblog.post.data;

import by.feedblog.post.domain.Category;
import by.feedblog.post.domain.CategoryInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Component
public class CategoryDataRestClient implements CategoryData {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Category save(Category category) {
        return restTemplate.exchange(URI.create("http://localhost:8084/category"), HttpMethod.POST, new HttpEntity<>(category), Category.class).getBody();
    }

    @Override
    public boolean existsByName(String name) {
       return restTemplate.getForEntity("http://localhost:8084/category/search/existsByName?name={name}", Boolean.class, name).getBody();
    }

    @Override
    public boolean existsById(long id) {
        Category category = restTemplate.getForEntity("http://localhost:8084/category/{id}", Category.class, id).getBody();
        return category != null;
    }

    @Override
    public Category findById(long id) {
        return restTemplate.getForEntity("http://localhost:8084/category/{id}", Category.class, id).getBody();
    }

    @Override
    public Category findByName(String name) {
        return restTemplate.getForEntity("http://localhost:8084/category/search/getByName?name={name}", Category.class, name).getBody();
    }

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = Objects.requireNonNull(restTemplate.getForObject("http://localhost:8084/category", CategoryInfoResponse.class)).getCategoryList();
        return  categoryList;

//        ResponseEntity<List<Category>> responseEntity = restTemplate.exchange("http://localhost:8084/category", HttpMethod.GET, null, new ParameterizedTypeReference<List<Category>>() {});
//        List<Category> categoryList = responseEntity.getBody();
//        return categoryList.stream()
//                .map(Category::getName)
//                .collect(Collectors.toList());

//        Category[] categories = restTemplate.getForObject("http://localhost:8084/category", Category[].class);
//        return new ArrayList<Category>(Arrays.asList(categories));

//        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity("http://localhost:8084/category", Object[].class);
//        Object[] objects = responseEntity.getBody();
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//        return Arrays.stream(objects)
//                .map(object -> mapper.convertValue(object, Category.class))
//                .map(Category::getName)
//                .collect(Collectors.toList());

//        List<Category> categories = restTemplate.exchange("http://localhost:8084/category", HttpMethod.GET, null, new ParameterizedTypeReference<List<Category>>() {
//        }).getBody();
//        return categories;
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete("http://localhost:8084/category/{id}", id);
    }

    @Override
    public void deleteByName(String name) {
        restTemplate.delete("http://localhost:8084/category/search/deleteByName?name={name}", name);
    }
}
