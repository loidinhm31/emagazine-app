package com.emagazine.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emagazine.api.entity.Comment;
import com.emagazine.api.entity.Post;
import com.emagazine.api.model.CommentDTO;
import com.emagazine.api.model.CommentForListViewDTO;
import com.emagazine.api.model.CommentRequestDTO;
import com.emagazine.api.repository.CommentRepository;
import com.emagazine.api.repository.PostRepository;
import com.emagazine.api.service.CommentService;
import com.emagazine.api.utils.ObjectMapperUtils;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDTO findById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);

        if (comment.isPresent()) {
            CommentDTO commentPOJO = ObjectMapperUtils.map(comment.get(), CommentDTO.class);

            return commentPOJO;
        }

        return null;
    }

    @Override
    public List<CommentDTO> findByPostId(Long postId) {

        List<Comment> comments = commentRepository.findByPostIdOrderByDateCreate(postId);

        if (!comments.isEmpty()) {
            List<CommentDTO> commentDTOs = ObjectMapperUtils.mapAll(comments, CommentDTO.class);

            return commentDTOs;
        }

        return null;
    }

    @Override
    public void save(CommentRequestDTO commentDTO) {
        // Get the post for Comment Entity to insert into database
        Optional<Post> thePost = postRepository.findById(commentDTO.getPostId());

        if (thePost.isPresent()) {

            Comment theComment = new Comment();
            theComment.setId(commentDTO.getId());
            theComment.setUsername(commentDTO.getUsername());
            theComment.setUserEmail(commentDTO.getUserEmail());
            theComment.setContent(commentDTO.getContent());
            theComment.setPost(thePost.get());

            commentRepository.save(theComment);
        }

    }

    @Override
    public List<CommentForListViewDTO> findAll(String keyword) {

        List<Comment> comments = commentRepository.findByContentContainingIgnoreCaseOrderByDateCreateDesc(keyword);

        List<CommentForListViewDTO> commentDTOs = ObjectMapperUtils.mapAll(comments, CommentForListViewDTO.class);

        return commentDTOs;
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentForListViewDTO> findByPostIdForAdmin(Long postId, String keyword) {
        if (keyword == null) {
            keyword = "";
        }
        List<Comment> comments = commentRepository.findByPostIdAndContentContainingIgnoreCaseOrderByDateCreateDesc(postId, keyword.trim());

        List<CommentForListViewDTO> commentDTOs = ObjectMapperUtils.mapAll(comments, CommentForListViewDTO.class);
        return commentDTOs;
    }
}

