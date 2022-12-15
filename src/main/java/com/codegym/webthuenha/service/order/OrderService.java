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
    public Iterable<Order> showOrderByHouseId(Long id) {
        return orderRepository.showOrderByHouseId(id);
    }

    @Override
    public Iterable<Order> getOrderByUserId(Long id) {
        return orderRepository.getOrderByUserId(id);
    }

    @Override
    public Iterable<Order> getOrderPast(Long id, Long start) {
        return orderRepository.getOrderPast(id, ((5 * start)));
    }

    public Iterable<Order> getListBookingByHouseOfUserId(Long id, Long start) {
        return orderRepository.getListBookingByHouseOfUserId(id, ((5 * start)));
    }

    @Override
    public Iterable<Order> getListBookingByUserId(Long id) {
        return orderRepository.getListBookingByUserId(id);
    }

    @Override
    public Iterable<Order> getOrderWaitConfirm(Long id, Long start) {
        return orderRepository.getOrderWaitConfirm(id, ((5 * start)));
    }

    @Override
    public Iterable<Order> getOrderPast(Long id) {
        return orderRepository.getOrderPast(id);
    }

    @Override
    public void updateStatusOrderCancel(Long id) {
        orderRepository.updateStatusOrderCancel(id);
    }
}
