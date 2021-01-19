package by.feedblog.cacheuserservice.storage;

import by.feedblog.cacheuserservice.domain.Key;
import by.feedblog.cacheuserservice.domain.User;
import by.feedblog.cacheuserservice.storage.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class UserCache {

    private HashMap<Key, User> userCache = new HashMap<>();

    public User save(Key key, User user) {
        if (userCache.containsKey(key)) {
            return user;
        } else {
            userCache.put(key, user);
        }
        return user;
    }

    public void delete(Key key) {
        userCache.remove(key);
    }

    public User findByKey(Key key){
        return userCache.get(key);
    }

    public int isEmpty(){
        return userCache.size();
    }

    public List<User> findAll(){
        List<User> users = new ArrayList<>();
        for(Key key: userCache.keySet()){
            users.add(userCache.get(key));
        }
        return users;
    }

    public User findUserById(long id){
        if (userCache.size() == 0) return null;
        for (User user : userCache.values()) {
            if(user.getId() == id){
                return user;
            }
        }
        throw new UserNotFoundException();
    }
}
