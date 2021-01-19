package by.feedblog.user.service.dto;

import by.feedblog.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListDto {
    private User[] user;
}
