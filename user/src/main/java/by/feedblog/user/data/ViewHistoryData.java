package by.feedblog.user.data;

import by.feedblog.user.domain.ViewHistory;

import java.util.List;

public interface ViewHistoryData {
    List<ViewHistory> findAllByUserId(long userId);
    ViewHistory findByPostId(long postId);
}
