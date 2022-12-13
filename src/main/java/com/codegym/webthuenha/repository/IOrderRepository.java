package com.codegym.webthuenha.repository;

import com.codegym.webthuenha.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    @Query(nativeQuery = true, value = "select * from orders where house_id = :id and ((orders.star_time <= :startTime and orders.end_time >= :startTime) or (orders.star_time <= :endTime and orders.end_time >= :endTime))")
    Iterable<Order> checkOrder(@Param("id") Long id, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    @Query(nativeQuery = true, value = "select * from orders where house_id = :id")
    Iterable<Order> showOrderByHouseId(@Param("id") Long id);
    @Query(nativeQuery = true, value = "select o.*, h.house_name as tennha, u.full_name as tenuser from orders o join houses h on h.id = o.house_id join users u on o.users_id = u.id  where h.user_id = :idabcd and o.order_status_id = 1")
    Iterable<Order> getListBooking(@Param("idabcd") Long id);
}
