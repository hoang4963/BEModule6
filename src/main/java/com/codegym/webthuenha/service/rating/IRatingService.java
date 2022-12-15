package com.codegym.webthuenha.service.rating;

import com.codegym.webthuenha.model.Rating;
import com.codegym.webthuenha.service.IGeneralService;

public interface IRatingService extends IGeneralService<Rating> {
    public Iterable<Rating> RatingByHouseId(Long id);
}
