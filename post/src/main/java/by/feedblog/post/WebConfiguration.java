package by.feedblog.post;

import by.feedblog.post.interceptor.AccessTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private AccessTokenInterceptor accessTokenInterceptor;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessTokenInterceptor)
                .addPathPatterns("/post", "/post/**").excludePathPatterns("/post/findAll")
                .addPathPatterns("/category", "/category/**")
                .addPathPatterns("/tag", "/tag/**")
                .addPathPatterns("/like", "/like/like**");
    }
}
