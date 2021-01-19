package by.feedblog.post.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(value = {
        "id",
        "login",
        "password",
        "name",
        "bornDate",
        "postList",
        "likes",
        "role",
        "createDate",
        "updateDate"
})
public class User {
    private long id;
    private String login;
    private String password;
    private Role role;
    private List<Post> postList;
    private List<Like> likes;
    private String name;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd.MM.yyyy hh:mm:ss")
    private Date createDate;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd.MM.yyyy hh:mm:ss")
    private Date updateDate;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd.MM.yyyy")
    private Date bornDate;
}
