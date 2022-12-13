package com.codegym.webthuenha.service.order;

import com.codegym.webthuenha.model.Order;
import com.codegym.webthuenha.service.IGeneralService;

import java.util.Date;

public interface IOrderService extends IGeneralService<Order> {
    Iterable<Order> getAllOrder();

    Iterable<Order> checkTimeOrder(Long id, Date startTime, Date endTime);


    Iterable<Order> getOrderPast(Long id);

    Iterable<Order> showOrderByHouseId(Long id);

    Iterable<Order> getListBookingByUserId(Long id);

}
