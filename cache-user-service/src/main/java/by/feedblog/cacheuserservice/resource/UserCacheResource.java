package by.feedblog.cacheuserservice.resource;

import by.feedblog.cacheuserservice.domain.Key;
import by.feedblog.cacheuserservice.domain.User;
import by.feedblog.cacheuserservice.resource.dto.RequestDto;
import by.feedblog.cacheuserservice.storage.UserCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/userCache")
public class UserCacheResource {

    @Autowired
    private UserCache userCache;

    @PostMapping(path = "/saveCache")
    public ResponseEntity<User> save(@RequestBody RequestDto requestDto){
        return ResponseEntity.ok(userCache.save(requestDto.getKey(), requestDto.getUser()));
    }

    @GetMapping(path = "/findInCache")
    public ResponseEntity<User> findPost(String method, String parameter){
        return ResponseEntity.ok(userCache.findByKey(new Key(method, parameter)));
    }

    @GetMapping(path = "/findById")
    public ResponseEntity<User> findUserById(long id){
        return ResponseEntity.ok(userCache.findUserById(id));
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userCache.findAll());
    }

    @GetMapping(path = "/isEmpty")
    public ResponseEntity<Integer> isEmpty(){
        return ResponseEntity.ok(userCache.isEmpty());
    }

    @DeleteMapping(path = "/deleteCache")
    public ResponseEntity<?> delete(@RequestBody Key key){
        userCache.delete(key);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
