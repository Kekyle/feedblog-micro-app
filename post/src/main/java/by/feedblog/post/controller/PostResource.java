package by.feedblog.post.controller;

import by.feedblog.post.domain.*;
import by.feedblog.post.service.PostService;
import by.feedblog.post.service.dto.LikeDto;
import by.feedblog.post.service.dto.PostCommentDto;
import by.feedblog.post.service.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/post")
public class PostResource {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> save(@RequestBody PostDto post){
        Post post1 = postService.save(post);
        return ResponseEntity.ok(post1);
    }

    @PostMapping(path = "/saveComment")
    public ResponseEntity<?> saveComment(@RequestBody PostCommentDto postCommentDto){
        postService.saveComment(postCommentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/likePost")
    public ResponseEntity<List<Like>> likePost(@RequestBody LikeDto likeDto){
        return ResponseEntity.ok(postService.likePost(likeDto));
    }

    @PostMapping(path = "/approve/{id}")
    public ResponseEntity<?> approve(@PathVariable long id, @RequestBody User user){
        postService.approve(id, user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<Post>> findAll(){
        return ResponseEntity.ok(postService.findAll());
    }


    @GetMapping(path = "/findAllByApproved")
    public ResponseEntity<List<Post>> findAllByApproved(){
        List<Post> allByApproved = postService.findAllByApproved();
        return ResponseEntity.ok(allByApproved);
    }

    @GetMapping(path = "/findAllNotApproved")
    public ResponseEntity<List<Post>> findAllNotApproved(){
        return ResponseEntity.ok(postService.findAllNotApproved());
    }

    @GetMapping(path = "/findAllByCategory")
    public ResponseEntity<List<Post>> findAllByCategory(Category category){
        return ResponseEntity.ok(postService.findAllByCategory(category));
    }

    @GetMapping(path = "/findAllByTags")
    public ResponseEntity<List<Post>> findAllByTags(Tag tag){
        return ResponseEntity.ok(postService.findAllByTags(tag));
    }

    @GetMapping(path = "/findById")
    public ResponseEntity<Post> findById(long id){
        return ResponseEntity.ok(postService.findById(id));
    }

    @GetMapping(path = "/findByTitle")
    public ResponseEntity<Post> findByTitle(String title){
        return ResponseEntity.ok(postService.findByTitle(title));
    }

    @GetMapping(path = "/findByDescription")
    public ResponseEntity<Post> findByDescription(String description){
        return ResponseEntity.ok(postService.findByDescription(description));
    }

    @PutMapping
    public ResponseEntity<Post> update(@RequestBody PostDto post){
        return ResponseEntity.ok(postService.update(post));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id){
        postService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteByTitle")
    public ResponseEntity<?> deleteByTitle(String title){
        postService.deleteByTitle(title);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteByDescription")
    public ResponseEntity<?> deleteByDescription(String description){
        postService.deleteByDescription(description);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
