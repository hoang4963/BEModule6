package com.codegym.webthuenha.controller.house;


import com.codegym.webthuenha.model.DTO.HouseDTO;
import com.codegym.webthuenha.model.House;
import com.codegym.webthuenha.model.HouseStatus;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("/create/{id}")
    public ResponseEntity<House> createHouse(@PathVariable("id") Long id,@RequestBody HouseDTO houseDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        House house = new House();
        house.setId(houseDTO.getId());
        house.setHouseName(houseDTO.getHouseName());
        house.setHouseAddress(houseDTO.getHouseAddress());
        house.setBathrooms(houseDTO.getBathrooms());
        house.setBedrooms(houseDTO.getBedrooms());
        house.setDescription(houseDTO.getDescription());
        house.setRent(houseDTO.getRent());
        house.setStatus(houseStatusService.findById(Long.parseLong("2")).get());
        Image image1 = new Image(houseDTO.getImage1());
        Image image2 = new Image(houseDTO.getImage2());
        Image image3 = new Image(houseDTO.getImage3());
        imageService.save(image1);
        imageService.save(image2);
        imageService.save(image3);
        List<Image> imageList = new ArrayList<>();
        imageList.add(imageService.findByName(image1.getImageName()).get());
        imageList.add(imageService.findByName(image2.getImageName()).get());
        imageList.add(imageService.findByName(image3.getImageName()).get());
        house.setImage(imageList);
//        house.getImage().add(imageService.findByName(houseDTO.getImage1()).get());
//        house.getImage().add(imageService.findByName(houseDTO.getImage2()).get());
//        house.getImage().add(imageService.findByName(houseDTO.getImage3()).get());
        house.setUser(userService.findById(id).get());
        houseService.save(house);
        return new ResponseEntity<>(houseService.save(house), HttpStatus.OK);
    }
    @PutMapping("/updateStatus/{id}/{idStatus}")
    public ResponseEntity<House> updateStatus(@PathVariable("id") Long id, @PathVariable("idStatus") Long idStatus){
        if (!houseService.findById(id).isPresent() || !houseStatusService.findById(idStatus).isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        House house = houseService.findById(id).get();
        HouseStatus status = houseStatusService.findById(idStatus).get();
        house.setStatus(status);
        houseService.save(house);
        return new ResponseEntity<>(house, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<House> updateHouse() {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<House> deleteHouse() {
        return null;
    }
    @GetMapping("imageString/{id}")
    public ResponseEntity<House> getOneHouse(@PathVariable Long id){
        Optional<House> optionalHouse = houseService.findById(id);
        if (!optionalHouse.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        House house = optionalHouse.get();
//        HouseImageDTO houseImageDTO = new HouseImageDTO();
//        houseImageDTO.setId(house.getId());
//        houseImageDTO.setHouseName(house.getHouseName());
//        houseImageDTO.setHouseAddress(house.getHouseAddress());
//        houseImageDTO.setBathrooms(house.getBathrooms());
//        houseImageDTO.setBedrooms(house.getBedrooms());
//        houseImageDTO.setDescription(house.getDescription());
//        houseImageDTO.setRent(house.getRent());
//        houseImageDTO.setUser(house.getUser());
//        houseImageDTO.setHouseStatus(house.getStatus());
//        houseImageDTO.setImage1(house.getImage().get(0).getImageName());
//        houseImageDTO.setImage2(house.getImage().get(1).getImageName());
//        houseImageDTO.setImage3(house.getImage().get(2).getImageName());
        return new ResponseEntity<>(house, HttpStatus.OK);
    }

    @GetMapping("/list5house")
    public ResponseEntity<Iterable<House>> show5HouseMax() {
        Iterable<House> users = houseService.get5HouseByView();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
