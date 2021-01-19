package by.feedblog.post.controller;

import by.feedblog.post.domain.Comment;
import by.feedblog.post.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/comment")
public class CommentResource {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> save(@RequestBody Comment comment){
        return ResponseEntity.ok(commentService.save(comment));
    }

    @PutMapping
    public ResponseEntity<Comment> update(@RequestBody Comment comment){
        return ResponseEntity.ok(commentService.update(comment));
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<Comment>> findAll(){
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping(path = "/findById")
    public ResponseEntity<Comment> findById(long id){
        return ResponseEntity.ok(commentService.findById(id));
    }

    @GetMapping(path = "/findByComment")
    public ResponseEntity<Comment> findByComment(String comment){
        return ResponseEntity.ok(commentService.findByComment(comment));
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<?> deleteById(long id){
        commentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteByComment")
    public ResponseEntity<?> deleteByComment(String comment){
        commentService.deleteByComment(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
