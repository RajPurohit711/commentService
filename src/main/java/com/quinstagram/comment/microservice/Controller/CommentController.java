package com.quinstagram.comment.microservice.Controller;
import com.quinstagram.comment.microservice.dto.CommentDto;
import com.quinstagram.comment.microservice.entity.Comment;
import com.quinstagram.comment.microservice.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    CommentService commentService;


    //        Todo Update Count on Post

    @RequestMapping(value = "/add",method = {RequestMethod.POST,RequestMethod.PUT})
    public void addComment(@RequestBody CommentDto commentDto){
        Comment comment=new Comment();
        BeanUtils.copyProperties(commentDto,comment);
        if (comment.getParentCommentId()!=null)
        {
            Optional<Comment> comment1=commentService.getCommentById(comment.getParentCommentId());
            if(comment1.isPresent())
                comment1.get().setChildCount(comment1.get().getChildCount()+1);
//            comment1.setChildCount(comment1.getChildCount()+1);

        }
        commentService.addComment(comment);
    }

    @GetMapping("/all")
    public Iterable<Comment> getAllComments(){
        return commentService.getAll();
    }

//optional PathVariable...only one for comments of post...two for comment of comments
    @GetMapping(value = {"post/{postId}","post/{postId}/{parentId}"})
    public Iterable<CommentDto> getCommentForPost(@PathVariable("postId") Long postId,@PathVariable(required = false,value = "parentId") Long parentId){
        Iterable<CommentDto> commentDtos=  new ArrayList<>();
        Iterable<Comment> comments=  commentService.findByPostIdAndParentCommentId(postId,parentId);
        for (Comment comment:comments)
        {
            CommentDto tempCommentDto=new CommentDto();
            BeanUtils.copyProperties(comment,tempCommentDto);
            ((ArrayList<CommentDto>) commentDtos).add(tempCommentDto);
        }
        return commentDtos;

    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Long id){
        commentService.deleteById(id);
        commentService.deleteAllByParentCommentId(id);

    }

    @DeleteMapping("/deleteByPostId/{id}")
    public void deleteByPostId(@PathVariable("id") Long id){
        commentService.deleteAllByPostId(id);

    }



}
