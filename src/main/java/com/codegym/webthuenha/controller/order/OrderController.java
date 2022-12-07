package com.codegym.webthuenha.controller.order;

import com.codegym.webthuenha.model.House;
import com.codegym.webthuenha.model.Order;
import com.codegym.webthuenha.service.order.IOrderService;
import com.codegym.webthuenha.service.orderStatus.IOrderStatusService;
import com.codegym.webthuenha.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
// show tất cả order
    @GetMapping("/orders")
    public ResponseEntity<Iterable<Order>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }
//    Xóa orders
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
    public ResponseEntity<Order> changeStatus(@PathVariable Long id, String status) {
        Optional<Order> orderOptional = orderService.findById(id);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Order order = orderOptional.get();
//        chưa biết set thẳng hay set như thế nào . còn đây là kiểu ăn sổi
//        order.setStatus("paid");
        orderService.save(order);
        return new ResponseEntity<>(orderOptional.get(), HttpStatus.OK);
    }
}
