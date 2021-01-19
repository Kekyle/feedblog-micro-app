package by.feedblog.cachepostservice.resource.dto;

import by.feedblog.cachepostservice.domain.Key;
import by.feedblog.cachepostservice.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    private Key key;
    private Post post;
}
