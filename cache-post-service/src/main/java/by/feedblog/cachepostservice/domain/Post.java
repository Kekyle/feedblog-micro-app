package by.feedblog.cachepostservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    private long id;
    private String title;
    private String description;
    private long viewCount;
    private User user;

    private Date createDate;

    private Date updateDate;

    private Category category;

    private List<Tag> tags;

    private List<Like> likes;

    private List<Comment> comments;

    private boolean approved;

}
