package by.feedblog.post.data;

import by.feedblog.post.domain.Category;
import by.feedblog.post.domain.Post;
import by.feedblog.post.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
public class PostDataRestClient implements PostData {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Post save(Post post) {
        return restTemplate.exchange(URI.create("http://localhost:8084/post"), HttpMethod.POST, new HttpEntity<>(post), Post.class).getBody();
    }

    @Override
    public Post getOne(long id) {
        return restTemplate.getForEntity("http://localhost:8084/post/{id}", Post.class, id).getBody();
    }

    @Override
    public Post findById(long id) {
        return restTemplate.getForEntity("http://localhost:8084/post/{id}", Post.class, id).getBody();
    }

    @Override
    public boolean existsById(long id) {
        Post post = restTemplate.getForEntity("http://localhost:8084/post/{id}", Post.class, id).getBody();
        return post != null;
    }

    @Override
    public List<Post> findAll() {
        return restTemplate.exchange("http://localhost:8084/post", HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {
        }).getBody();
    }

    @Override
    public List<Post> findAllByApproved(boolean isApproved) {
        return restTemplate.exchange("http://localhost:8084/post/search/findAllByApproved?isApproved={isApproved}", HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {
        }).getBody();
    }

    @Override
    public List<Post> findAllByCategory(Category category) {
        return restTemplate.exchange("http://localhost:8084/post/search/findAllByCategory", HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {
        }).getBody();
    }

    @Override
    public List<Post> findAllByTags(Tag tag) {
        return restTemplate.exchange("http://localhost:8084/post/search/findAllByTag", HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>() {
        }).getBody();
    }

    @Override
    public boolean existsByTitle(String title) {
        return restTemplate.getForEntity("http://localhost:8084/post/search/existsByTitle?title={title}", Boolean.class, title).getBody();
    }

    @Override
    public Post findByTitle(String title) {
        return restTemplate.getForEntity("http://localhost:8084/post/search/findByTitle?title={title}", Post.class, title).getBody();
    }

    @Override
    public boolean existsByDescription(String description) {
        return restTemplate.getForEntity("http://localhost:8084/post/search/existsByDescription?description={description}", Boolean.class, description).getBody();
    }

    @Override
    public Post findByDescription(String description) {
        return restTemplate.getForEntity("http://localhost:8084/post/search/findByDescription?description={description}", Post.class, description).getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete("http://localhost:8084/post/{id}", id);
    }

    @Override
    public void deleteByTitle(String title) {
        restTemplate.delete("http://localhost:8084/post/search/deleteByTitle?title={title}", title);
    }

    @Override
    public void deleteByDescription(String description) {
        restTemplate.delete("http://localhost:8084/post/search/deleteByDescription?description={description}", description);
    }
}
