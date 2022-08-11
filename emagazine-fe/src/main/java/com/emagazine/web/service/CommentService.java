package com.emagazine.web.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.emagazine.web.model.Comment;
import com.emagazine.web.model.CommentForListView;
import com.emagazine.web.model.request.CommentRequest;

public interface CommentService {
    void saveComment(CommentRequest theComment);

    List<Comment> fetchCommentsForPost(Long postId);

    List<CommentForListView> findAll(String keyword, HttpSession session);

    List<CommentForListView> findByPostIdForAdminPage(Long id, String keyword, HttpSession session);

    boolean deleteById(Long id, HttpSession session);

    Comment findById(Long id);
}
