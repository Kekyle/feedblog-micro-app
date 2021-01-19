package by.feedblog.post.data;

import by.feedblog.post.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
public class CommentDataRestClient implements CommentData {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Comment save(Comment comment) {
        return restTemplate.exchange(URI.create("http://localhost:8084/comment"), HttpMethod.POST, new HttpEntity<>(comment), Comment.class).getBody();
    }

    @Override
    public boolean existsById(long id) {
        Comment comment = restTemplate.getForEntity("http://localhost:8084/comment/{id}", Comment.class, id).getBody();
        return comment != null;
    }

    @Override
    public boolean existsByComment(String comment) {
        return restTemplate.getForEntity("http://localhost:8084/comment/search/existsByComment?comment={comment}", Boolean.class, comment).getBody();
    }

    @Override
    public Comment findById(long id) {
        return restTemplate.getForEntity("http://localhost:8084/comment/{id}", Comment.class, id).getBody();
    }

    @Override
    public Comment findByComment(String comment) {
        return restTemplate.getForEntity("http://localhost:8084/comment/search/findByComment?comment={comment}", Comment.class, comment).getBody();
    }

    @Override
    public List<Comment> findAll() {
        return restTemplate.exchange("http://localhost:8084/comment", HttpMethod.GET, null, new ParameterizedTypeReference<List<Comment>>() {
        }).getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete("http://localhost:8084/comment/{id}", id);
    }

    @Override
    public void deleteByComment(String comment) {
        restTemplate.delete("http://localhost:8084/comment/search/deleteByComment?comment={comment}", comment);
    }
}
