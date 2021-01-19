package by.feedblog.postdatarest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "like", path = "like")
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByPost(Post post);
    List<Like> findAllByPost(Post post);
    void deleteByPost(Post post);
}
