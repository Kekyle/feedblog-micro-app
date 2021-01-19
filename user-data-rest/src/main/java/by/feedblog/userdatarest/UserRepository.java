package by.feedblog.userdatarest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User getByLogin(String login);

    List<User> getAllByName(String name);

    List<User> getAllByBornDate(Date bornDate);

    void deleteByLogin(String login);

    void deleteById(long id);

    boolean existsById(long id);

    boolean existsByLogin(String login);

    boolean existsByName(String name);

    boolean existsByBornDate(Date bornDate);
}
