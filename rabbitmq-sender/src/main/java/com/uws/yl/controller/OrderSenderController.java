package com.uws.yl.controller;

import com.uws.yl.bean.Order;
import com.uws.yl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class OrderSenderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value="/testSender")
    public String testSender(){
        Order order = new Order();
        order.setName("你好");
        order.setMessageId(UUID.randomUUID().toString());
        orderService.createorder(order);
        return "success";
    }
}
