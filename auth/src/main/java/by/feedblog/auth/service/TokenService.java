package by.feedblog.auth.service;

import by.feedblog.auth.domain.Token;
import by.feedblog.auth.repository.TokenRepository;
import by.feedblog.auth.service.exception.TokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public Token save(Token token){
        if (tokenRepository.existsByToken(token.getToken())){
            throw new TokenException();
        } else {
            return tokenRepository.save(token);
        }
    }

    public void deleteTokenByLogin(String login){
        if (tokenRepository.existsByLogin(login)){
            tokenRepository.deleteByLogin(login);
        } else {
            throw new TokenException();
        }
    }

    public boolean containsTokenByLogin(String login){
        return tokenRepository.existsByLogin(login);
    }

    public boolean containsToken(String token){
        if (token == null) {
            throw new TokenException();
        } else {
            return tokenRepository.existsByToken(token);
        }
    }
}
