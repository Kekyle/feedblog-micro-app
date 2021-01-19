package by.feedblog.user.data;

import by.feedblog.user.domain.ViewHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ViewHistoryRestClient implements ViewHistoryData {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<ViewHistory> findAllByUserId(long userId) {
        return restTemplate.exchange("http://localhost:8083/viewHistory/search/findAllByUserId?id={id}", HttpMethod.GET, null, new ParameterizedTypeReference<List<ViewHistory>>() {
        }).getBody();
    }

    @Override
    public ViewHistory findByPostId(long postId) {
        return restTemplate.getForEntity("http://localhost:8083/viewHistory/search/findByPostId?postId={postId}", ViewHistory.class, postId).getBody();
    }
}
