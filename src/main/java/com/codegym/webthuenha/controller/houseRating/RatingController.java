package com.codegym.webthuenha.controller.houseRating;

import com.codegym.webthuenha.model.Rating;
import com.codegym.webthuenha.service.rating.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    IRatingService ratingService;
    @GetMapping("/list")
    public ResponseEntity<Iterable<Rating>> HouseRating() {
        Iterable<Rating> users = ratingService.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/getstar/{id}")
    public ResponseEntity<Iterable<Rating>> RatingByHouseId(@PathVariable Long id){
        return new ResponseEntity<>(ratingService.RatingByHouseId(id),HttpStatus.OK);
    }


}
