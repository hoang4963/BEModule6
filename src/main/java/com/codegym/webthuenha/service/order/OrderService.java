package com.codegym.webthuenha.service.order;

import com.codegym.webthuenha.model.Order;
import com.codegym.webthuenha.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Iterable<Order> getAllOrder() {
        return null;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Iterable<Order> checkTimeOrder(Long id, Date startTime, Date endTime) {
        return orderRepository.checkOrder(id, startTime, endTime);
    }

    @Override
    public Optional<Order> showOrderByHouseId(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Iterable<Order> getOrderPast(Long id) {
        return orderRepository.getOrderPast(id);
    }
}
