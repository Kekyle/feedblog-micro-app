package by.feedblog.post.service;

import by.feedblog.post.data.CommentData;
import by.feedblog.post.data.PostData;
import by.feedblog.post.domain.Comment;
import by.feedblog.post.service.exception.CommentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentData commentData;

    @Autowired
    private PostData postData;

    public Comment save(Comment comment){
        return commentData.save(comment);
    }

    public Comment update(Comment comment){
        if (commentData.existsById(comment.getId())){
            return commentData.save(comment);
        } else {
            throw new CommentNotFoundException();
        }
    }

    public Comment findById(long id){
        if (commentData.existsById(id)){
            return commentData.findById(id);
        } else {
            throw new CommentNotFoundException();
        }
    }

    public Comment findByComment(String comment){
        if (commentData.existsByComment(comment)){
            return commentData.findByComment(comment);
        } else {
            throw new CommentNotFoundException();
        }
    }

    public List<Comment> findAll(){
        return commentData.findAll();
    }

//    public List<Comment> findAllByPostId(long postId){
//        if (postRepository.existsById(postId)){
//            return commentRepository.findAllByPostId(postId);
//        } else {
//            throw new PostNotFoundException();
//        }
//    }

    public void deleteById(long id){
        if (commentData.existsById(id)){
            commentData.deleteById(id);
        } else {
            throw new CommentNotFoundException();
        }
    }

    public void deleteByComment(String comment){
        if (commentData.existsByComment(comment)){
            commentData.deleteByComment(comment);
        } else {
            throw new CommentNotFoundException();
        }
    }
}
