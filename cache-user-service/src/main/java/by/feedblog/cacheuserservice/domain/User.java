package by.feedblog.cacheuserservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long id;

    private String login;

    private String password;

    private Role role;

//    private List<Post> postList;

//    private List<Like> likes;

    private String name;

    private Date createDate;

    private Date updateDate;

    private Date bornDate;

    private String method;

    private String parameter;
}
