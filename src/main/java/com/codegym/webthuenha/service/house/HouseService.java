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
}
