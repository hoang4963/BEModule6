package com.codegym.webthuenha.controller.order;

import com.codegym.webthuenha.model.DTO.OrderDTO;
import com.codegym.webthuenha.model.House;
import com.codegym.webthuenha.model.Order;
import com.codegym.webthuenha.model.OrderStatus;
import com.codegym.webthuenha.model.User;
import com.codegym.webthuenha.service.BookingService;
import com.codegym.webthuenha.service.house.IHouseService;
import com.codegym.webthuenha.service.order.IOrderService;
import com.codegym.webthuenha.service.orderStatus.IOrderStatusService;
import com.codegym.webthuenha.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderStatusService orderStatusService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IHouseService houseService;

    @Autowired
    private BookingService bookingService;
    // show tất cả order
    @GetMapping("/orders")
    public ResponseEntity<Iterable<Order>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    //    Tìm kiếm order theo id
    @GetMapping("/orders/{id}")
    public ResponseEntity<Optional<Order>> showOderById(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/user/house/orders/{user_id}")
    public ResponseEntity<Iterable<Order>> getListBookingByUserId(@PathVariable Long user_id) {
        return new ResponseEntity<>(orderService.getListBookingByUserId(user_id), HttpStatus.OK);
    }
    @GetMapping("/orders/house/{house_id}")
    public ResponseEntity<Iterable<Order>> showOrderByHouseId(@PathVariable Long house_id) {
        return new ResponseEntity<>(orderService.showOrderByHouseId(house_id), HttpStatus.OK);
    }

    @PostMapping("/orders/{id}")
    public ResponseEntity<Optional<Order>> createOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        List lists;
        lists = (List) orderService.checkTimeOrder(id, orderDTO.getStartTime(), orderDTO.getEndTime());
        System.out.println(lists.size());
        System.out.println(orderService.checkTimeOrder(id, orderDTO.getStartTime(), orderDTO.getEndTime()));
        Date date;


//        lấy time hiện hiện tại
        date = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
//        boolean kt = orderDTO.getStartTime().before(date);
//        boolean kt1 = orderDTO.getEndTime().before(date);
        if (orderDTO.getStartTime().after(date) || orderDTO.getEndTime().after(date)) {
            if (lists.size() != 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                House house;
                house = houseService.findById(orderDTO.getHouseId()).get();
                User user;
                user = userService.findById(orderDTO.getUsersId()).get();
                OrderStatus orderStatus;
                orderStatus = orderStatusService.findById(orderDTO.getOrderStatusID()).get();
                Order order = new Order();
                order.setId(orderDTO.getId());
                order.setUser(user);
                order.setHouse(house);
                order.setStatus(orderStatus);
//                order.setStatus();
                order.setStartTime(orderDTO.getStartTime());
                order.setEndTime(orderDTO.getEndTime());
                order.setCreateTime(orderDTO.getCreateTime());
                try {
                    orderService.save(order);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //    Xóa orders theo id
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id) {
        Optional<Order> orderOptional = orderService.findById(id);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.delete(id);
        return new ResponseEntity<>(orderOptional.get(), HttpStatus.NO_CONTENT);
    }

    //    check sửa trạng thái
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> changeStatus(@PathVariable Long id, Long statusId) {
        Order order = orderService.findById(id).get();
        OrderStatus orderStatus = orderStatusService.findById(statusId).get();
        order.setStatus(orderStatus);
        orderService.save(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
