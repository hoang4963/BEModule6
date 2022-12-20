package com.codegym.webthuenha.service.house;

import com.codegym.webthuenha.model.House;
import com.codegym.webthuenha.service.IGeneralService;

public interface IHouseService extends IGeneralService<House> {
    Iterable<House>get5HouseByRent();

    Iterable<House> findByUserId(Long id);

   Iterable<House> findHouseByAll(String bedrooms,
                                  String bathrooms,
                                  String address,
                                  long rentMin, long rentMax,
                                  String endTime, String startTime);

}
