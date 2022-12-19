package com.codegym.webthuenha.repository;

import com.codegym.webthuenha.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IHouseRepository extends JpaRepository<House, Long> {
    @Query(value = "select h.*, count(h.id) as luotthue from houses h join orders o on h.id = o.house_id group by h.id order by luotthue desc limit 5", nativeQuery = true)
    Iterable<House> get5HouseByRent();

    @Query(nativeQuery = true, value = "select h.* from houses h join orders on h.id = orders.house_id where h.bathroooms = :bath and h.bedroom = :bed and h.house_address like :address and ((order_status_id = 2 or order_status_id = 3) and ((orders.start_time > :endTime) or (orders.end_time < :startTime))) and (h.rent <= :maxRent and h.rent >= :minRent) group by h.id")
    Iterable<House> searchAllHouse(@Param("bath") int bath, @Param("bed") int bed, @Param("address") String address, @Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("maxRent") Long maxRent, @Param("minRent") Long minRent);
}
