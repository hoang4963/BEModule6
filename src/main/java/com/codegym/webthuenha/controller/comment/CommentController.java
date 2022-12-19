package com.codegym.webthuenha.controller.comment;

import com.codegym.webthuenha.model.Comment;
import com.codegym.webthuenha.model.DTO.CommentDTO;
import com.codegym.webthuenha.model.Order;
import com.codegym.webthuenha.service.comment.ICommentService;
import com.codegym.webthuenha.service.house.HouseService;
import com.codegym.webthuenha.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    ICommentService commentService;
    @Autowired
    HouseService houseService;
    @Autowired
    UserService userService;
    @GetMapping("/list")
    public ResponseEntity<Iterable<Comment>> HouseComment() {
        Iterable<Comment> users = commentService.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("/housecomment")
    public ResponseEntity<Comment> HouseCommentDTO(@RequestBody CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setHouse(houseService.findById(commentDTO.getHouseId()).get());
        comment.setUser(userService.findById(commentDTO.getUserId()).get());
       commentService.save(comment);
       return new ResponseEntity<>(comment,HttpStatus.OK);
    }
    @GetMapping("/createcomment/{id}")
    public ResponseEntity<Iterable<Comment>> createComment(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(commentService.CommentByHouseId(id),HttpStatus.OK);
    }
}
