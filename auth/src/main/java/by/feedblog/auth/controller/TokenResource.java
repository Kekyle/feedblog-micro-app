package by.feedblog.auth.controller;

import by.feedblog.auth.controller.exception.UserLoginException;
import by.feedblog.auth.domain.Token;
import by.feedblog.auth.domain.User;
import by.feedblog.auth.service.TokenService;
import by.feedblog.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = "/auth")
public class TokenResource {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(path = "/registration")
    public ResponseEntity<User> registration(@RequestBody User user){
        User user1 = restTemplate.exchange(URI.create("http://localhost:8082/user"), HttpMethod.POST, new HttpEntity<>(user), User.class).getBody();
        return ResponseEntity.ok(user1);
    }

    @GetMapping(path = "/login")
    public String login(String login, String password){
        User user = userService.findByLogin(login);
        if (user.getPassword().equals(password)){
            UUID token = UUID.randomUUID();
            tokenService.save(new Token(user.getLogin(), token.toString()));
            return token.toString();
        }
        throw new UserLoginException();
    }

    @Transactional
    @GetMapping(path = "/deleteToken")
    public void logout(String login){
        tokenService.deleteTokenByLogin(login);
    }

    @GetMapping(path = "/existsByToken")
    public ResponseEntity<Boolean> existsByToken(String token){
        return ResponseEntity.ok(tokenService.containsToken(token));
    }
}
