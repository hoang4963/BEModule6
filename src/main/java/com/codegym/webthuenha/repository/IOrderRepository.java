package com.codegym.webthuenha.repository;

import com.codegym.webthuenha.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    @Query(nativeQuery = true, value = "select * from orders where house_id = :id and ((orders.start_time <= :startTime and orders.end_time >= :startTime) or (orders.start_time <= :endTime and orders.end_time >= :endTime))")
    Iterable<Order> checkOrder(@Param("id") Long id, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    @Query(nativeQuery = true, value = "select *from orders where house_id = :id")
    Iterable<Order> showOrderByHouseId(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select * from orders where users_id = :id limit :start , 5")
    Iterable<Order> getOrderPast(@Param("id") Long id, @Param("start") Long start);

    @Query(nativeQuery = true, value = "select * from orders where users_id = :id")
    Iterable<Order> getOrderByUserId(@Param("id") Long id);
}
