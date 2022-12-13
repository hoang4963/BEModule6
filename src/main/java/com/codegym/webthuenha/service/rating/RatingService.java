package com.codegym.webthuenha.service.rating;

import com.codegym.webthuenha.model.Rating;
import com.codegym.webthuenha.repository.IRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService implements IRatingService {
    @Autowired
    IRatingRepository ratingRepository;
    @Override
    public Iterable<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Optional<Rating> findById(Long id) {
        return ratingRepository.findById(id);
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void delete(Long id) {
        ratingRepository.deleteById(id);
    }
}
