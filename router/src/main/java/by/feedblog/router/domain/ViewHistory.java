package by.feedblog.router.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewHistory {

    private long id;

    private long postId;
    private long userId;

    private Date date;

    public ViewHistory(long postId, long userId, Date date) {
        this.postId = postId;
        this.userId = userId;
        this.date = date;
    }
}
