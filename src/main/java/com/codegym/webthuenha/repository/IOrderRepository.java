package com.codegym.webthuenha.repository;

import com.codegym.webthuenha.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    @Query(nativeQuery = true, value = "select * from orders where house_id = :id and (orders.order_status_id <> 1 and ((orders.start_time <= :startTime and orders.end_time >= :startTime) or (orders.start_time <= :endTime and orders.end_time >= :endTime)))")
    Iterable<Order> checkOrder(@Param("id") Long id, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    @Query(nativeQuery = true, value = "select * from orders where house_id = :id and (orders.order_status_id =2)")
    Iterable<Order> showOrderByHouseId(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select * from orders where order_status_id <> 1 and users_id = :id limit :start , 5")
    Iterable<Order> getOrderPast(@Param("id") Long id, @Param("start") Long start);

    @Query(nativeQuery = true, value = "select * from orders where users_id = :id")
    Iterable<Order> getOrderByUserId(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select o.* from orders o join houses h on h.id = o.house_id where h.user_id = :idabcd and o.order_status_id = 1")
    Iterable<Order> getListBookingByUserId(@Param("idabcd") Long id);
    @Query(nativeQuery = true, value = "select o.* from orders o join houses h on h.id = o.house_id where h.user_id = :userId and o.order_status_id = 1 limit :start , 5 order by o.start_time ")
    Iterable<Order> getListBookingByHouseOfUserId(@Param("userId")Long userId, @Param("start")Long start);

    @Query(nativeQuery = true, value = "select * from orders where order_status_id <> 1 and users_id = :id")
    Iterable<Order> getOrderPast(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select * from orders where order_status_id = 1 and users_id = :id limit :start , 5")
    Iterable<Order> getOrderWaitConfirm(@Param("id") Long id, @Param("start") Long start);

    @Query(nativeQuery = true, value = "UPDATE orders SET order_status_id = 4 WHERE id = :id ")
    void updateStatusOrderCancel(@Param("id") Long id);
}
