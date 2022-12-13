package com.codegym.webthuenha.repository;

import com.codegym.webthuenha.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IHouseReposiroty extends JpaRepository<House, Long> {
    @Query(value = "select h.*, count(h.id) as luotthue from houses h join orders o on h.id = o.house_id group by h.id order by luotthue desc limit 5" , nativeQuery = true)
    Iterable<House>get5HouseByView();
}
