package by.feedblog.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder(value = {
//        "id",
//        "login",
//        "password",
//        "name",
//        "bornDate",
//        "postList",
//        "likes",
//        "role",
//        "createDate",
//        "updateDate"
//})
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String login;

    private String password;

//    private Role role;

//    private List<Post> postList;

//    private List<Like> likes;

    private String name;

    private Date createDate;

    private Date updateDate;

    private Date bornDate;
}
