package com.codegym.webthuenha.service.house;

import com.codegym.webthuenha.model.House;
import com.codegym.webthuenha.service.IGeneralService;

import java.util.Date;

public interface IHouseService extends IGeneralService<House> {
    Iterable<House> get5HouseByRent();

//    Iterable<House> searchAllHouse(int bath, int bed, String address, Date startTime, Date endTime, Long maxRent, Long minRent);

    Iterable<House> findByUserId(Long id);

}
