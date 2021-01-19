package by.feedblog.postdatarest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "post", path = "post")
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    Post getByTitle(String title);
    Post getByDescription(String description);
    List<Post> getAllByDescription(String description);
    Post getByCategory(Category category);
    Post getByTags(Tag tag);
    boolean existsByTitle(String title);
    boolean existsByDescription(String description);
    boolean existsByCategory(Category category);
    void deleteByTitle(String title);
    void deleteByDescription(String description);
    void deleteByCreateDate(Date createDate);
}
