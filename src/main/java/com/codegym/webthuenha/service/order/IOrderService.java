package com.codegym.webthuenha.service.order;

import com.codegym.webthuenha.model.Order;
import com.codegym.webthuenha.service.IGeneralService;

import java.util.Date;

public interface IOrderService extends IGeneralService<Order> {
    Iterable<Order> getAllOrder();

    Iterable<Order> checkTimeOrder(Long id, Date startTime, Date endTime);


    Iterable<Order> getOrderPast(Long id, Long start);

    Iterable<Order> showOrderByHouseId(Long id);

    Iterable<Order> getOrderByUserId(Long id);

    Iterable<Order> getListBookingByHouseOfUserId(Long id, Long start);

}
