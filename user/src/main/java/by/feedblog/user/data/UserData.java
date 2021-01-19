package by.feedblog.user.data;

import by.feedblog.user.domain.User;
import by.feedblog.user.service.dto.BornDateDto;

import java.util.Date;
import java.util.List;

public interface UserData {
    User save(User user);
    boolean existsByLogin(String login);
    boolean existsById(long id);
    boolean existsByName(String name);
    List<User> findAll();
    User findById(long id);
    User findByLogin(String login);
    List<User> findAllByName(String name);
    List<User> findAllByBornDate(Date bornDate);
    boolean existsByBornDate(Date date);
    void deleteById(long id);
    void deleteByLogin(String login);
}
