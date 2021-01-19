package by.feedblog.post.service;

import by.feedblog.post.data.LikeData;
import by.feedblog.post.domain.Like;
import by.feedblog.post.domain.Post;
import by.feedblog.post.service.exception.LikeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeData likeData;

    public Like save(Like like){
        if (likeData.existsByPost(like.getPost())){
            throw new LikeException();
        } else {
            return likeData.save(like);
        }
    }

    public void deleteLikeFromPost(Post post){
        if (likeData.existsByPost(post)){
            likeData.deleteByPost(post);
        } else {
            throw new LikeException();
        }
    }

    public List<Like> findAllByPost(Post post){
        return likeData.findAllByPost(post);
    }
}
