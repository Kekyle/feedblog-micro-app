package by.feedblog.postdatarest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "tag", path = "tag")
public interface TagRepository extends PagingAndSortingRepository<Tag, Long> {
    Tag getByName(String name);
    void deleteByName(String name);
    boolean existsByName(String name);
}
