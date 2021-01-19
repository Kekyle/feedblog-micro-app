package by.feedblog.post.service;

import by.feedblog.post.data.LikeData;
import by.feedblog.post.data.PostData;
import by.feedblog.post.data.TagData;
import by.feedblog.post.data.UserData;
import by.feedblog.post.domain.*;
import by.feedblog.post.service.dto.LikeDto;
import by.feedblog.post.service.dto.PostCommentDto;
import by.feedblog.post.service.dto.PostDto;
import by.feedblog.post.service.dto.TagDto;
import by.feedblog.post.service.exception.PostNotFoundException;
import by.feedblog.post.service.exception.PostTitleException;
import by.feedblog.post.service.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostData postData;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserData userData;

    @Autowired
    private LikeData likeData;

    @Autowired
    private TagData tagData;

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private ViewHistoryRepository viewHistoryRepository;

    public Post save(PostDto post) {
        int isEmpty = restTemplate.getForEntity("http://localhost:8086/postCache/isEmpty", Integer.class).getBody();
        if (isEmpty == 0) {
                if (postData.existsByTitle(post.getTitle())) {
                    throw new PostTitleException("Post with this title is already exist");
                } else {
                    return getPost(post);
                }
            } else {
            Post post1 = restTemplate.getForEntity("http://localhost:8086/postCache/findInCache?method={post.getMethod()}&parameter={post.getParameter()}", Post.class, post.getMethod(), post.getParameter()).getBody();
            if (post1 != null){
                return post1;
            } else {
                return getPost(post);
            }
        }
    }

    private Post getPost(PostDto post) {
        Post currentPost = postMapper.dtoToDomain(post);
        List<TagDto> tagDto = post.getTagDto();
        List<Tag> tags = new ArrayList<>();
        for (TagDto dto : tagDto) {
            Tag tag = tagData.findById(dto.getId());
            tags.add(tag);
        }
        currentPost.setTags(tags);
        currentPost.setCreateDate(new Date());
        currentPost.setUpdateDate(new Date());
        postData.save(currentPost);
        restTemplate.exchange(URI.create("http://localhost:8086/postCache/saveCache"), HttpMethod.POST, new HttpEntity<>(post), Post.class);
        return currentPost;
    }

    public void saveComment(PostCommentDto postCommentDto) {
        if (postData.existsById(postCommentDto.getId())) {
            Post one = postData.getOne(postCommentDto.getId());
            one.getComments().add(new Comment(postCommentDto.getComment().getComment()));
            postData.save(one);
        } else {
            throw new PostNotFoundException();
        }
    }

    public List<Like> likePost(LikeDto like) {
        Post post = postData.findById(like.getPostId());
        User user = userData.getOne(like.getUserId());
        List<Like> likes = post.getLikes();
        for (Like like1 : likes) {
            if (like1.getUser().getId() != like.getUserId()) {
                likeData.save(new Like(post, user));
            }
        }
        return likeData.findAllByPost(post);
    }

    public void approve(long id, User user) {
        if (postData.existsById(id) && user.getRole().equals(Role.ADMIN)) {
            Post post = postData.getOne(id);
            post.setApproved(true);
            postData.save(post);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

    public Post update(PostDto post) {
        if (postData.existsById(post.getId())) {
            Post currentPost = postMapper.dtoToDomain(post);
            currentPost.setUpdateDate(new Date());
            currentPost.setApproved(false);
            return postData.save(currentPost);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

    public List<Post> findAll() {
        return postData.findAll();
    }

    public List<Post> findAllByApproved() {
        return postData.findAllByApproved(true);
    }

    public List<Post> findAllNotApproved() {
        return postData.findAllByApproved(false);
    }

    public List<Post> findAllByCategory(Category category) {
        return postData.findAllByCategory(category);
    }

    public List<Post> findAllByTags(Tag tag) {
        return postData.findAllByTags(tag);
    }

    public Post findById(long id) {
        if (postData.existsById(id)) {
            Post post1 = restTemplate.getForEntity("http://localhost:8086/postCache/findById?id={id}", Post.class, id).getBody();
            if (post1 != null){
                return post1;
            } else {
                Post post = postData.findById(id);
                PostDto postDto = postMapper.domainToDto(post);
                postDto.setMethod("findById");
                postDto.setParameter(""+id);
                restTemplate.exchange(URI.create("http://localhost:8086/postCache/saveCache"), HttpMethod.POST, new HttpEntity<>(postDto), Post.class);
                return post;
            }
//            Post post = postData.findById(id);
//            updateViewCount(post);
//            ViewHistory viewHistory = null;
//            List<ViewHistory> allByUserId = viewHistoryRepository.findAllByUserId(post.getUser().getId());
//            if (allByUserId.size() != 0) {
//                viewHistory = allByUserId.get(0);
//            }
//            if (allByUserId.size() == 10) {
//                viewHistoryRepository.delete(viewHistory);
//            }
//            viewHistoryRepository.save(new ViewHistory(post.getId(), post.getUser().getId(), new Date()));
        } else throw new PostNotFoundException("Post not found");
    }

    private void updateViewCount(Post post) {
        long viewCount = post.getViewCount();
        post.setViewCount(++viewCount);
        postData.save(post);
    }

    public Post findByTitle(String title) {
        if (postData.existsByTitle(title)) {
            Post post = postData.findByTitle(title);
            updateViewCount(post);
            return postData.findByTitle(title);
        } else throw new PostTitleException();
    }

    public Post findByDescription(String description) {
        if (postData.existsByDescription(description)) {
            Post post = postData.findByDescription(description);
            updateViewCount(post);
            return postData.findByDescription(description);
        } else throw new PostNotFoundException("Post not found");
    }

    public void deleteById(long id) {
        if (postData.existsById(id)) {
            postData.deleteById(id);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

    public void deleteByTitle(String title) {
        if (postData.existsByTitle(title)) {
            postData.deleteByTitle(title);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

    public void deleteByDescription(String description) {
        if (postData.existsByDescription(description)) {
            postData.deleteByDescription(description);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }
}
