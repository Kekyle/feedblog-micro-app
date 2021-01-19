package by.feedblog.router.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    private long id;

    private String login;

    private String token;

    public Token(String login, String token) {
        this.login = login;
        this.token = token;
    }
}
