package by.feedblog.cachepostservice.storage;

import by.feedblog.cachepostservice.domain.Key;
import by.feedblog.cachepostservice.domain.Post;
import by.feedblog.cachepostservice.storage.exception.PostNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class PostCache {

    private HashMap<Key, Post> postCache = new HashMap<>();

    public Post save(Key key, Post post) {

//        new Key("getById", "1");

        if (postCache.containsKey(key)) {
            return post;
        } else {
            postCache.put(key, post);
        }
        return post;
    }

    public void delete(Key key) {
        postCache.remove(key);
    }

    public Post findByKey(Key key){
        return postCache.get(key);
    }

    public int isEmpty(){
        return postCache.size();
    }

    public List<Post> findAll(){
        List<Post> posts = new ArrayList<>();
        for(Key key: postCache.keySet()){
            posts.add(postCache.get(key));
        }
        return posts;
    }

    public Post findPostById(long id){
        if (postCache.size() == 0) return null;
        for (Post post : postCache.values()) {
            if(post.getId() == id){
                return post;
            }
        }
        throw new PostNotFoundException();
    }

}
