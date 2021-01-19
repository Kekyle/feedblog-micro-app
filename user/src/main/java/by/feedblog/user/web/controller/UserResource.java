package by.feedblog.user.web.controller;

import by.feedblog.user.domain.User;
import by.feedblog.user.domain.ViewHistory;
import by.feedblog.user.service.UserService;
import by.feedblog.user.service.dto.BornDateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> save( @RequestBody User user){
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user){
        return ResponseEntity.ok(userService.update(user));
    }

    @PutMapping(path = "/updateRole")
    public ResponseEntity<User> updateRole(@RequestBody User user){
        return ResponseEntity.ok(userService.updateRoleToAdmin(user));
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<?> deleteById(long id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteByLogin")
    public ResponseEntity<?> deleteByLogin(String login){
        userService.deleteByLogin(login);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/viewHistory")
    public ResponseEntity<List<ViewHistory>> viewHistory(long userId){
        return ResponseEntity.ok(userService.findTenLastViewPost(userId));
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(path = "/findByLogin")
    public ResponseEntity<User> findByLogin(String login){
        return ResponseEntity.ok(userService.findByLogin(login));
    }

    @GetMapping(path = "/findAllByName")
    public ResponseEntity<List<User>> findAllByName(String name){
        return ResponseEntity.ok(userService.findAllByName(name));
    }

    @GetMapping(path = "/findById")
    public ResponseEntity<User> findById(long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping(path = "/findAllByBornDate")
    public ResponseEntity<List<User>> findAllByBornDate(@RequestBody BornDateDto bornDate){
        return ResponseEntity.ok(userService.findAllByBornDate(bornDate));
    }
}
