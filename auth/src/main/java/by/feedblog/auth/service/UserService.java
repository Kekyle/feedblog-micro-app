package by.feedblog.auth.service;

import by.feedblog.auth.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    public User findByLogin(String login){
        return restTemplate.getForEntity("http://localhost:8083/user/search/getByLogin?login={login}", User.class, login).getBody();
    }
}
