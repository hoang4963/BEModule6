package com.codegym.webthuenha.service.order;

import com.codegym.webthuenha.model.Order;
import com.codegym.webthuenha.service.IGeneralService;

public interface IOrderService extends IGeneralService<Order> {
    Iterable<Order> getAllOrder();
}
