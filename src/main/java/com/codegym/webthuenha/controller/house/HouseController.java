package com.codegym.webthuenha.controller.house;


import com.codegym.webthuenha.model.HouseDTO;
import com.codegym.webthuenha.model.House;
import com.codegym.webthuenha.model.Image;
import com.codegym.webthuenha.service.house.IHouseService;
import com.codegym.webthuenha.service.housestatus.IHouseStatusService;
import com.codegym.webthuenha.service.image.IImageService;
import com.codegym.webthuenha.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/house")
public class HouseController {

    @Autowired
    IHouseService houseService;

    @Autowired
    IImageService imageService;

    @Autowired
    IHouseStatusService houseStatusService;

    @Autowired
    IUserService userService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<House>> showAllHouse() {
        Iterable<House> users = houseService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<House> createHouse(@RequestBody HouseDTO houseDTO, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        House house = new House();
        house.setHouseName(houseDTO.getHouseName());
        house.setHouseAddress(houseDTO.getHouseAddress());
        house.setBathrooms(houseDTO.getBathrooms());
        house.setBedrooms(houseDTO.getBedrooms());
        house.setDescription(houseDTO.getDescription());
        house.setRent(houseDTO.getRent());
        Image image1 = new Image();
        Image image2 = new Image();
        Image image3 = new Image();
        image1.setImageName(houseDTO.getImage1());
        image2.setImageName(houseDTO.getImage2());
        image3.setImageName(houseDTO.getImage3());
        imageService.save(image1);
        imageService.save(image2);
        imageService.save(image3);
        house.getImage().add(imageService.findByName(image1.getImageName()).get());
        house.getImage().add(imageService.findByName(image2.getImageName()).get());
        house.getImage().add(imageService.findByName(image3.getImageName()).get());
        house.setStatus(houseStatusService.findById(houseDTO.getHouseStatus()).get());
        house.setUser(userService.findById(houseDTO.getUserId()).get());
        houseService.save(house);
        return new ResponseEntity<>(houseService.save(house), HttpStatus.OK);
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
