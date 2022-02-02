package com.quinstagram.comment.microservice.repository;

import com.quinstagram.comment.microservice.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Long> {

     List<Comment> findAllByPostIdAndParentCommentId(Long postId,Long parentCommentId);

     void deleteAllByParentCommentId(Long parentCommentId);

     void deleteAllByPostId(Long postId);

//     @Query(select * from )
//     List<Comment> get();
}
