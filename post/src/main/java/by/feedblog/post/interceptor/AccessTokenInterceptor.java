package by.feedblog.post.interceptor;

import by.feedblog.post.interceptor.exception.AccessException;
import by.feedblog.post.service.TokenService;
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
