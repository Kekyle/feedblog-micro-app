package by.feedblog.auth.repository;

import by.feedblog.auth.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    void deleteByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByToken(String token);
}
