package com.quinstagram.comment.microservice.service.impl;

import com.quinstagram.comment.microservice.entity.Comment;
import com.quinstagram.comment.microservice.repository.CommentRepository;
import com.quinstagram.comment.microservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Optional<Comment> getCommentById(Long id){

        return commentRepository.findById(id);
    }

    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Iterable<Comment> getAll(){
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> findByPostIdAndParentCommentId(Long postId, Long parentCommentId){
        return commentRepository.findAllByPostIdAndParentCommentId(postId,parentCommentId);
    }

    @Transactional
    @Override
    public void deleteAllByParentCommentId(Long parentCommentId){
        commentRepository.deleteAllByParentCommentId(parentCommentId);
    }

    @Transactional
    @Override
    public void deleteById(Long id){
        commentRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAllByPostId(Long postId) {
        commentRepository.deleteAllByPostId(postId);
    }
}
