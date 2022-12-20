package com.codegym.webthuenha.service.house;

import com.codegym.webthuenha.model.House;
import com.codegym.webthuenha.repository.IHouseReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class HouseService implements IHouseService{
    @Autowired
    IHouseReposiroty houseReposiroty;
    @Override
    public Iterable<House> findAll() {
        return houseReposiroty.findAll();
    }

    @Override
    public Optional<House> findById(Long id) {
        return houseReposiroty.findById(id);
    }

    @Override
    public House save(House house) {
        return houseReposiroty.save(house);
    }

    @Override
    public void delete(Long id) {
        houseReposiroty.deleteById(id);
    }

    @Override
    public Iterable<House> get5HouseByRent() {
        return houseReposiroty.get5HouseByRent();
    }

    @Override
    public Iterable<House> findByUserId(Long id) {
        return houseReposiroty.findHouseByUserId(id);
    }

    @Override
    public Iterable<House> findHouseByAll(String bedrooms,
                                          String bathrooms,
                                          String address,
                                          long rentMin, long rentMax,
                                          String endTime, String startTime) {
        if (bedrooms == null || bedrooms == ""){
            bedrooms = ".*";
        }
        if (bathrooms == null || bathrooms == ""){
            bathrooms = ".*";
        }
        if (address == null || address == ""){
            address = ".*";
        }
        return houseReposiroty.findHouseByAll(bedrooms, bathrooms, address, rentMin, rentMax, endTime, startTime);
    }
}
