package by.feedblog.post.service.dto;

import by.feedblog.post.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private long id;

    private String title;

    private String description;

    private User user;

    private boolean approved;

    private Date updateDate;

    private CategoryDto categoryDto;

    private List<TagDto> tagDto;

    private String method;

    private String parameter;

}
