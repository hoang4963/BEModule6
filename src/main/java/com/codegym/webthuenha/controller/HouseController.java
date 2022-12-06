package com.codegym.webthuenha.controller;


import com.codegym.webthuenha.model.House;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Repository
@CrossOrigin("*")
@RequestMapping("/house")
@PropertySource("classpath:application.properties")
public class HouseController {

    @GetMapping("/list")
    public ResponseEntity<Iterable<House>> showAllHouse() {

        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<House> createHouse(){
        return null;
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<House> updateHouse() {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<House> deleteHouse() {
        return null;
    }
}
