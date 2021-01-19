package by.feedblog.user.interceptor;

import by.feedblog.user.service.TokenService;
import by.feedblog.user.web.exception.AccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AccessTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("X-FeedBlog-Token");
        if (tokenService.containsToken(header)) {
            return true;
        }
        throw new AccessException("Token" + header + "not found");
    }
}
