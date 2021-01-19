package by.feedblog.user.service;

import by.feedblog.user.data.UserData;
import by.feedblog.user.data.ViewHistoryData;
import by.feedblog.user.domain.Role;
import by.feedblog.user.domain.User;
import by.feedblog.user.domain.ViewHistory;
import by.feedblog.user.service.dto.BornDateDto;
import by.feedblog.user.service.exception.UserIsAlreadyExistException;
import by.feedblog.user.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserData userData;

    @Autowired
    private ViewHistoryData viewHistoryData;

    @Autowired
    private RestTemplate restTemplate;

    public User save(User user) {
        if (userData.existsByLogin(user.getLogin())) {
            throw new UserIsAlreadyExistException();
        } else {
            int isEmpty = restTemplate.getForEntity("http://localhost:8087/userCache/isEmpty", Integer.class).getBody();
            if (isEmpty == 0) {
                user.setCreateDate(new Date());
                user.setUpdateDate(new Date());
//            user.setRole(Role.USER);
                restTemplate.exchange(URI.create("http://localhost:8087/userCache/saveCache"), HttpMethod.POST, new HttpEntity<>(user), User.class);
                return userData.save(user);
            } else {
                User user1 = restTemplate.getForEntity("http://localhost:8087/userCache/findInCache?method={user.getMethod()}&parameter={user.getParameter()}", User.class, user.getMethod(), user.getParameter()).getBody();
                if (user1 != null){
                    return user1;
                } else {
                    restTemplate.exchange(URI.create("http://localhost:8087/userCache/saveCache"), HttpMethod.POST, new HttpEntity<>(user), User.class);
                    return userData.save(user);
                }
            }
        }
    }

    public User update(User user) {
        if (userData.existsById(user.getId())) {
            user.setUpdateDate(new Date());
            return userData.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }

    public User updateRoleToAdmin(User user){
        if (userData.existsById(user.getId())){
            user.setUpdateDate(new Date());
//            user.setRole(Role.ADMIN);
            return userData.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }

    public List<User> findAll(){
        return userData.findAll();
    }

    public User findById(long id){
        if (userData.existsById(id)){
            return userData.findById(id);
        } else {
            throw new UserNotFoundException();
        }
    }

    public User findByLogin(String login){
        if (userData.existsByLogin(login)){
            return userData.findByLogin(login);
        } else {
            throw new UserNotFoundException();
        }
    }

    public List<ViewHistory> findTenLastViewPost(long userId){
        return viewHistoryData.findAllByUserId(userId);
    }

    public List<User> findAllByName(String name){
        if (userData.existsByName(name)){
            return userData.findAllByName(name);
        } else {
            throw new UserNotFoundException();
        }
    }

    public List<User> findAllByBornDate(BornDateDto bornDate){
        Date bornDate1 = bornDate.getBornDate();
        if (userData.existsByBornDate(bornDate1)){
            return userData.findAllByBornDate(bornDate1);
        } else {
            throw new UserNotFoundException();
        }
    }

    public void deleteById(long id){
        if (userData.existsById(id)){
            userData.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }
    }

    public void deleteByLogin(String login){
        if (userData.existsByLogin(login)){
            userData.deleteByLogin(login);
        } else {
            throw new UserNotFoundException();
        }
    }
}
