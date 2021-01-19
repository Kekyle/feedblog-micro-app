package by.feedblog.cacheuserservice.resource.dto;

import by.feedblog.cacheuserservice.domain.Key;
import by.feedblog.cacheuserservice.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    private Key key;
    private User user;
}
