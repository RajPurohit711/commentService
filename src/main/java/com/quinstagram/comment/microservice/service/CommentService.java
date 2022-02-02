package com.quinstagram.comment.microservice.service;

import com.quinstagram.comment.microservice.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    public void addComment(Comment comment);

    public Optional<Comment> getCommentById(Long id);

    public Iterable<Comment> getAll();

    public List<Comment> findByPostIdAndParentCommentId(Long postId, Long parentCommentId);

    public void deleteAllByParentCommentId(Long parentCommentId);

    public void deleteById(Long id);

    void deleteAllByPostId(Long postId);
}
