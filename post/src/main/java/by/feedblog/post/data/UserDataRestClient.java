package by.feedblog.post.data;

import by.feedblog.post.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserDataRestClient implements UserData {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User getOne(long id) {
        return restTemplate.getForEntity("http://localhost:8083/user/search/getById?id={id}", User.class, id).getBody();
    }
}
