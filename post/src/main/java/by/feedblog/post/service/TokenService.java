package by.feedblog.post.service;

import by.feedblog.post.domain.Token;
import by.feedblog.post.service.exception.TokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenService {

    @Autowired
    private RestTemplate restTemplate;

    public boolean containsToken(String token) {
        return restTemplate.getForEntity("http://localhost:8085/auth/existsByToken?token={token}", Boolean.class, token).getBody();
    }
}
