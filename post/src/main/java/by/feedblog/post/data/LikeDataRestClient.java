package by.feedblog.post.data;

import by.feedblog.post.domain.Like;
import by.feedblog.post.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
public class LikeDataRestClient implements LikeData {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Like save(Like like) {
        return restTemplate.exchange(URI.create("http://localhost:8084/like"), HttpMethod.POST, new HttpEntity<>(like), Like.class).getBody();
    }

    @Override
    public boolean existsByPost(Post post) {
        return restTemplate.getForEntity("http://localhost:8084/like/search/existsByPost", Boolean.class, post).getBody();
    }

    @Override
    public List<Like> findAllByPost(Post post) {
        return restTemplate.exchange("http://localhost:8084/like", HttpMethod.GET, null, new ParameterizedTypeReference<List<Like>>() {
        }).getBody();
    }

    @Override
    public void deleteByPost(Post post) {
        restTemplate.delete("http://localhost:8084/comment/search/deleteByPost", post);
    }
}
