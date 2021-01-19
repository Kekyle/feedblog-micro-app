package by.feedblog.router.resource;

import by.feedblog.router.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class RouterResource {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(path = "/login")
    public ResponseEntity<String> login(String login, String password){
        String token = restTemplate.getForEntity("http://localhost:8085/auth/login?login={login}&password={password}", String.class, login, password).getBody();
        return ResponseEntity.ok(token);
    }

    @GetMapping(path = "/deleteToken")
    public ResponseEntity<?> deleteToken(String login){
        restTemplate.delete("http://localhost:8085/deleteToken?login={login}", login);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/category")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        Category category1 = restTemplate.exchange(URI.create("http://localhost:8081/category"), HttpMethod.POST, new HttpEntity<>(category), Category.class).getBody();
        return ResponseEntity.ok(category1);
    }

    @GetMapping(path = "/category/findById/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable long id) {
        Category category = restTemplate.getForEntity("http://localhost:8081/category/findById/{id}", Category.class, id).getBody();
        return ResponseEntity.ok(category);
    }

    @GetMapping(path = "/category/findByName")
    public ResponseEntity<Category> findByName(String name) {
        Category category = restTemplate.getForEntity("http://localhost:8081/category/findByName?name={name}", Category.class, name).getBody();
        return ResponseEntity.ok(category);
    }

    @DeleteMapping(path = "/category/deleteById")
    public ResponseEntity<?> deleteById(long id) {
        restTemplate.delete("http://localhost:8081/category/deleteById?id={id}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/category/deleteByName")
    public ResponseEntity<?> deleteByName(String name) {
        restTemplate.delete("http://localhost:8081/category/deleteByName?name={name}", name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/tag")
    public ResponseEntity<Tag> save(@RequestBody Tag tag) {
        Tag tag1 = restTemplate.exchange(URI.create("http://localhost:8081/tag"), HttpMethod.POST, new HttpEntity<>(tag), Tag.class).getBody();
        return ResponseEntity.ok(tag1);
    }

    @GetMapping(path = "/tag/findById")
    public ResponseEntity<Tag> findTagById(long id) {
        Tag tag = restTemplate.getForEntity("http://localhost:8081/tag/findById?id={id}", Tag.class, id).getBody();
        return ResponseEntity.ok(tag);
    }

    @GetMapping(path = "/tag/findByName")
    public ResponseEntity<Tag> findTagByName(String name) {
        Tag tag = restTemplate.getForEntity("http://localhost:8081/tag/findByName?name={name}", Tag.class, name).getBody();
        return ResponseEntity.ok(tag);
    }

    @DeleteMapping(path = "/tag/deleteById")
    public ResponseEntity<?> deleteTagById(long id) {
        restTemplate.delete("http://localhost:8081/tag/deleteById?id={id}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/comment")
    public ResponseEntity<Comment> save(@RequestBody Comment comment) {
        Comment comment1 = restTemplate.exchange(URI.create("http://localhost:8081/comment"), HttpMethod.POST, new HttpEntity<>(comment), Comment.class).getBody();
        return ResponseEntity.ok(comment1);
    }

    @GetMapping(path = "/comment/findById")
    public ResponseEntity<Comment> findCommentById(long id) {
        Comment comment = restTemplate.getForEntity("http://localhost:8081/comment/findById?id={id}", Comment.class, id).getBody();
        return ResponseEntity.ok(comment);
    }

    @GetMapping(path = "/comment/findByComment")
    public ResponseEntity<Comment> findCommentByName(String comment) {
        Comment comment1 = restTemplate.getForEntity("http://localhost:8081/comment/findByComment?comment={comment}", Comment.class, comment).getBody();
        return ResponseEntity.ok(comment1);
    }

    @DeleteMapping(path = "/comment/deleteById")
    public ResponseEntity<?> deleteCommentById(long id) {
        restTemplate.delete("http://localhost:8081/comment/deleteById?id={id}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/like")
    public ResponseEntity<Like> save(@RequestBody Like like) {
        Like like1 = restTemplate.exchange(URI.create("http://localhost:8081/like"), HttpMethod.POST, new HttpEntity<>(like), Like.class).getBody();
        return ResponseEntity.ok(like1);
    }

    @GetMapping(path = "/like/findAllByPost")
    public ResponseEntity<List<Like>> findTagByName(Post post) {
        List<Like> likes = restTemplate.exchange("http://localhost:8081/post/findAllByPost", HttpMethod.GET, null, new ParameterizedTypeReference<List<Like>>() {
        }).getBody();
        return ResponseEntity.ok(likes);
    }

    @DeleteMapping(path = "/like/deleteLike")
    public ResponseEntity<?> deleteLike(Post post) {
        restTemplate.delete("http://localhost:8081/like/deleteLike", post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/post")
    public ResponseEntity<Post> save(@RequestBody Post post) {
        Post post1 = restTemplate.exchange(URI.create("http://localhost:8081/post"), HttpMethod.POST, new HttpEntity<>(post), Post.class).getBody();
        return ResponseEntity.ok(post1);
    }

    @GetMapping(path = "/post/findById")
    public ResponseEntity<Post> findPostById(long id) {
        Post post = restTemplate.getForEntity("http://localhost:8081/post/findById?id={id}", Post.class, id).getBody();
        return ResponseEntity.ok(post);
    }

    @GetMapping(path = "/post/findByTitle")
    public ResponseEntity<Post> findByTitle(String title) {
        Post post = restTemplate.getForEntity("http://localhost:8081/post/findByTitle?title={title}", Post.class, title).getBody();
        return ResponseEntity.ok(post);
    }

    @GetMapping(path = "/post/findByDescription")
    public ResponseEntity<Post> findByDescription(String description) {
        Post post = restTemplate.getForEntity("http://localhost:8081/post/findByDescription?description={description}", Post.class, description).getBody();
        return ResponseEntity.ok(post);
    }

    @DeleteMapping(path = "/post/deleteById")
    public ResponseEntity<?> deletePostById(long id) {
        restTemplate.delete("http://localhost:8081/post/deleteById?id={id}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/post/deleteByTitle")
    public ResponseEntity<?> deleteByTitle(String title) {
        restTemplate.delete("http://localhost:8081/post/deleteByTitle?title={title}", title);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/post/deleteByDescription")
    public ResponseEntity<?> deleteByDescription(String description) {
        restTemplate.delete("http://localhost:8081/post/deleteByDescription?description={description}", description);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/registration")
    public ResponseEntity<User> save(@RequestBody User user) {
        User user1 = restTemplate.exchange(URI.create("http://localhost:8082/user"), HttpMethod.POST, new HttpEntity<>(user), User.class).getBody();
        return ResponseEntity.ok(user1);
    }

    @GetMapping(path = "/user/findById")
    public ResponseEntity<User> findUserById(long id) {
        User user = restTemplate.getForEntity("http://localhost:8082/user/findById?id={id}", User.class, id).getBody();
        return ResponseEntity.ok(user);
    }

    @GetMapping(path = "/user/findByLogin")
    public ResponseEntity<User> findByLogin(String login) {
        User user = restTemplate.getForEntity("http://localhost:8082/user/findByLogin?login={login}", User.class, login).getBody();
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(path = "/user/deleteById")
    public ResponseEntity<?> deleteUserById(long id) {
        restTemplate.delete("http://localhost:8082/user/deleteById?id={id}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/user/deleteByLogin")
    public ResponseEntity<?> deleteByLogin(String login) {
        restTemplate.delete("http://localhost:8082/user/deleteByLogin?login={login}", login);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
