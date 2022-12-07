package com.codegym.webthuenha.repository;

import com.codegym.webthuenha.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
    @Query(nativeQuery = true, value = "select  * from `images` where image_name = :name ")
    Optional<Image> searchImageByImageName(@Param("name") String name);
}
