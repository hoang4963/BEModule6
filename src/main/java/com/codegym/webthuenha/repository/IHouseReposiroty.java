package com.codegym.webthuenha.repository;

import com.codegym.webthuenha.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

@Repository
public interface IHouseReposiroty extends JpaRepository<House, Long> {
    @Query(value = "select h.*, count(h.id) as luotthue from houses h join orders o on h.id = o.house_id group by h.id order by luotthue desc limit 5" , nativeQuery = true)
    Iterable<House>get5HouseByRent();

    @Query(nativeQuery = true, value = "select * from houses where user_id = :id ")
    Iterable<House> findHouseByUserId(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select * from houses h join orders on h.id = orders.house_id where" +
            " ((order_status_id = 2 or order_status_id = 3)) " +
            "and h.bedrooms regexp :bedrooms " +
            "and h.bathrooms regexp :bathrooms " +
            "and h.house_address regexp :address " +
            "and (h.rent > :rentMin and h.rent < :rentMax) " +
            "and ((orders.start_time > :endTime) or (orders.end_time < :startTime))")
    Iterable<House> findHouseByAll(@Param("bedrooms") String bedrooms,
                                   @Param("bathrooms") String bathrooms,
                                   @Param("address") String address,
                                   @Param("rentMin") long rentMin, @Param("rentMax") long rentMax,
                                   @Param("endTime") String endTime, @Param("startTime") String startTime);
}
