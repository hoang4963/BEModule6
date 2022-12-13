package com.codegym.webthuenha.controller.comment;

import com.codegym.webthuenha.model.Comment;
import com.codegym.webthuenha.service.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    ICommentService commentService;
    @GetMapping("/list")
    public ResponseEntity<Iterable<Comment>> HouseComment() {
        Iterable<Comment> users = commentService.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
