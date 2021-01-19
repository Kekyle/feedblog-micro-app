package by.feedblog.cachepostservice.resource;

import by.feedblog.cachepostservice.domain.Key;
import by.feedblog.cachepostservice.domain.Post;
import by.feedblog.cachepostservice.resource.dto.RequestDto;
import by.feedblog.cachepostservice.storage.PostCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/postCache")
public class PostCacheResource {

    @Autowired
    private PostCache postCache;

    @PostMapping(path = "/saveCache")
    public ResponseEntity<Post> save(@RequestBody RequestDto requestDto){
        return ResponseEntity.ok(postCache.save(requestDto.getKey(), requestDto.getPost()));
    }

    @GetMapping(path = "/findInCache")
    public ResponseEntity<Post> findPost(String method, String parameter){
        return ResponseEntity.ok(postCache.findByKey(new Key(method, parameter)));
    }

    @GetMapping(path = "/findById")
    public ResponseEntity<Post> findPostById(long id){
        return ResponseEntity.ok(postCache.findPostById(id));
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<Post>> findAll(){
        return ResponseEntity.ok(postCache.findAll());
    }

    @GetMapping(path = "/isEmpty")
    public ResponseEntity<Integer> isEmpty(){
        return ResponseEntity.ok(postCache.isEmpty());
    }

    @DeleteMapping(path = "/deleteCache")
    public ResponseEntity<?> delete(@RequestBody Key key){
        postCache.delete(key);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
