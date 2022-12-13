package com.codegym.webthuenha.service.house;

import com.codegym.webthuenha.model.House;
import com.codegym.webthuenha.service.IGeneralService;

public interface IHouseService extends IGeneralService<House> {
    Iterable<House>get5HouseByRent();

}
