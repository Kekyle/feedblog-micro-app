package by.feedblog.post.service.mapper;

import by.feedblog.post.domain.Category;
import by.feedblog.post.domain.Post;
import by.feedblog.post.domain.Tag;
import by.feedblog.post.service.dto.CategoryDto;
import by.feedblog.post.service.dto.PostDto;
import by.feedblog.post.service.dto.TagDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostMapper {

    public Post dtoToDomain(PostDto postDto) {
        return getPost(postDto);
    }

    public PostDto domainToDto(Post post) {
        return getPostDto(post);
    }

    public List<Post> dtoListToDomainList(List<PostDto> postDtoList) {
        List<Post> postList = new ArrayList<>();
        for (PostDto postDto : postDtoList) {
            Post post = getPost(postDto);
            postList.add(post);
        }
        return postList;
    }

    public List<PostDto> domainListToDtoList(List<Post> postList) {
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post : postList) {
            PostDto postDTO = getPostDto(post);
            postDtoList.add(postDTO);
        }
        return postDtoList;
    }

    private Post getPost(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setApproved(postDto.isApproved());
        post.setCreateDate(null);
        post.setUpdateDate(null);
        post.setUser(postDto.getUser());
        post.setCategory(new Category(postDto.getCategoryDto().getId(), postDto.getCategoryDto().getName()));
        List<Tag> tags = new ArrayList<>();
        List<TagDto> tagDTO = postDto.getTagDto();
        for (TagDto tagDto : tagDTO) {
            Tag tag = new Tag();
            tag.setId(tagDto.getId());
            tag.setName(tagDto.getName());
            tags.add(tag);
        }
        post.setTags(tags);
        return post;
    }

    private PostDto getPostDto(Post post) {
        PostDto postDTO = new PostDto();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setApproved(post.isApproved());
        postDTO.setUser(post.getUser());
        if (post.getCategory() != null) {
            postDTO.setCategoryDto(new CategoryDto(post.getCategory().getId(), post.getCategory().getName()));
        }
        List<TagDto> tagDtoList = new ArrayList<>();
        if (post.getTags() != null) {
            List<Tag> tags = post.getTags();
            for (Tag tag : tags) {
                TagDto tagDto = new TagDto();
                if (tag.getId() != 0) {
                    tagDto.setId(tag.getId());
                    tagDto.setName(tag.getName());
                    tagDtoList.add(tagDto);
                } else break;
            }
            postDTO.setTagDto(tagDtoList);
        }
        return postDTO;
    }
}
