package by.feedblog.post.data;

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
public class TagDataRestClient implements TagData {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Tag save(Tag tag) {
        return restTemplate.exchange(URI.create("http://localhost:8084/tag"), HttpMethod.POST, new HttpEntity<>(tag), Tag.class).getBody();
    }

    @Override
    public boolean existsById(long id) {
        Tag tag = restTemplate.getForEntity("http://localhost:8084/tag/{id}", Tag.class, id).getBody();
        return tag!= null;
    }

    @Override
    public boolean existsByName(String name) {
        return restTemplate.getForEntity("http://localhost:8084/tag/search/existsByName?name={name}", Boolean.class, name).getBody();
    }

    @Override
    public Tag findById(long id) {
        return restTemplate.getForEntity("http://localhost:8084/tag/{id}", Tag.class, id).getBody();
    }

    @Override
    public Tag findByName(String name) {
        return restTemplate.getForEntity("http://localhost:8084/tag/search/getByName?name={name}", Tag.class, name).getBody();
    }

    @Override
    public List<Tag> findAll() {
        return restTemplate.exchange("http://localhost:8084/tag", HttpMethod.GET, null, new ParameterizedTypeReference<List<Tag>>() {
        }).getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete("http://localhost:8084/tag/{id}", id);
    }

    @Override
    public void deleteByName(String name) {
        restTemplate.delete("http://localhost:8084/tag/search/deleteByName?name={name}", name);
    }
}
