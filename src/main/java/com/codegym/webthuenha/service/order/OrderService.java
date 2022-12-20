package com.codegym.webthuenha.service.order;

import com.codegym.webthuenha.model.DTO.Income;
import com.codegym.webthuenha.model.House;
import com.codegym.webthuenha.model.Order;
import com.codegym.webthuenha.model.OrderStatus;
import com.codegym.webthuenha.model.User;
import com.codegym.webthuenha.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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

//    service có được phép gọi service của thằng khác hay không. (mặt thiết kê)
//    hay chỉ phép được gọi từ controller đến service.
//
//    @Override
//    public Iterable<Order> createOrder(Long id, Date startTime, Date endTime) {
//        List lists;
//        lists = (List)checkTimeOrder(id, startTime, endTime);
//        System.out.println(lists);
//        if (lists.size() != 0 ) {
//            return null;
//        } else {
//            House house;
//            house = houseService.findById(orderDTO.getHouseId()).get();
//            User user;
//            user = userService.findById(orderDTO.getUsersId()).get();
//            OrderStatus orderStatus;
//            orderStatus = orderStatusService.findById(orderDTO.getOrderStatusID()).get();
//            Order order = new Order();
//            order.setId(orderDTO.getId());
//            order.setUser(user);
//            order.setHouse(house);
//            order.setStatus(orderStatus);
//            order.setStartTime(orderDTO.getStartTime());
//            order.setEndTime(orderDTO.getEndTime());
//            order.setCreateTime(orderDTO.getCreateTime());
//            order.calculateIncome(orderDTO.getEndTime(), orderDTO.getStartTime(), house.getRent());
//            try {
//                orderService.save(order);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
////        }
//        return ;
//    }

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

    @Override
    public Iterable<Order> showOrderByHouseIdStatus1(Long id) {
        return orderRepository.showOrderByHouseIdStatus1(id);
    }

    @Override
    public Iterable<Order> getIncome(Income income) {
        Long houseId = Long.parseLong(income.getHouseId());
        String[] splits = income.getMonth().split("-");
        Integer month = Integer.parseInt(splits[1]);
        Integer year = Integer.parseInt(splits[0]);
        return orderRepository.getIncome(houseId, month, year);
    }
}
