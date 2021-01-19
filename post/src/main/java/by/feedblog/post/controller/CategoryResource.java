package by.feedblog.post.controller;

import by.feedblog.post.domain.Category;
import by.feedblog.post.service.CategoryService;
import by.feedblog.post.service.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody CategoryDto category){
        return ResponseEntity.ok(categoryService.save(category));
    }

    @PutMapping
    public ResponseEntity<Category> update(@RequestBody CategoryDto category){
        return ResponseEntity.ok(categoryService.update(category));
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<Category>> findAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping(path = "/existsByName")
    public ResponseEntity<Boolean> existsByName(String name){
        return ResponseEntity.ok(categoryService.existsByName(name));
    }

    @GetMapping(path = "/findById/{id}")
    public ResponseEntity<Category> findById(@PathVariable long id){
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @GetMapping(path = "/findByName")
    public ResponseEntity<Category> findByName(String name){
        return ResponseEntity.ok(categoryService.findByName(name));
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<?> deleteById(long id){
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteByName")
    public ResponseEntity<?> deleteByName(String name){
        categoryService.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
