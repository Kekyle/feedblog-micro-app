package by.feedblog.user.data;

import by.feedblog.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Date;
import java.util.List;

@Component
public class UserDataRestClient implements UserData {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User save(User user) {
        return restTemplate.exchange(URI.create("http://localhost:8083/user"), HttpMethod.POST, new HttpEntity<>(user), User.class).getBody();
    }

    @Override
    public boolean existsByLogin(String login) {
        return restTemplate.getForEntity("http://localhost:8083/user/search/existsByLogin?login={login}", Boolean.class, login).getBody();
    }

    @Override
    public boolean existsById(long id) {
        User user = restTemplate.getForEntity("http://localhost:8083/user/{id}", User.class, id).getBody();
        return user != null;
    }

    @Override
    public boolean existsByName(String name) {
        return restTemplate.getForEntity("http://localhost:8083/user/search/existsByName?name={name}", Boolean.class, name).getBody();
    }

    @Override
    public List<User> findAll() {
        return restTemplate.exchange("http://localhost:8083/user", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        }).getBody();
    }

    @Override
    public User findById(long id) {
        return restTemplate.getForEntity("http://localhost:8083/user/{id}", User.class, id).getBody();
    }

    @Override
    public User findByLogin(String login) {
        return restTemplate.getForEntity("http://localhost:8083/user/search/findByLogin?login={login}", User.class, login).getBody();
    }

    @Override
    public List<User> findAllByName(String name) {
        return restTemplate.exchange("http://localhost:8083/user/search/findAllByName?name={name}", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        }).getBody();
    }

    @Override
    public List<User> findAllByBornDate(Date bornDate) {
        return restTemplate.exchange("http://localhost:8083/user/search/findAllByBornDate?bornDate={bornDate}", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        }).getBody();
    }

    @Override
    public boolean existsByBornDate(Date date) {
        return restTemplate.getForEntity("http://localhost:8083/user/search/existsByBornDate?date={date}", Boolean.class, date).getBody();
    }

    @Override
    public void deleteById(long id) {
        restTemplate.delete("http://localhost:8083/user/{id}", id);
    }

    @Override
    public void deleteByLogin(String login) {
        restTemplate.delete("http://localhost:8083/user/search/deleteByLogin?login={login}", login);
    }
}
