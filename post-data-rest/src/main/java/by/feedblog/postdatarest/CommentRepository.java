package by.feedblog.postdatarest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "comment", path = "comment")
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
    Comment getByComment(String comment);
    void deleteByComment(String comment);
    boolean existsByComment(String comment);
}
