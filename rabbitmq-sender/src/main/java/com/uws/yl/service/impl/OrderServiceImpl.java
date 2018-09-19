package com.uws.yl.service.impl;

import com.uws.yl.bean.MessageInfo;
import com.uws.yl.bean.Order;
import com.uws.yl.contant.Contant;
import com.uws.yl.mapper.MessageInfoMapper;
import com.uws.yl.mapper.OrderMapper;
import com.uws.yl.send.OrderSender;
import com.uws.yl.service.OrderService;
import com.uws.yl.util.DateUtil;
import com.uws.yl.util.FastJsonConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MessageInfoMapper messageInfoMapper;
    @Autowired
    private OrderSender orderSender;

    @Override
    public void createorder(Order order){
        orderMapper.insertOrder(order);
        MessageInfo messageInfo =  new MessageInfo();
        //消息的唯一ID
        messageInfo.setMessageId(order.getMessageId());
        messageInfo.setMessage(FastJsonConvertUtil.converObjectToJson(order));
        messageInfo.setStatus(Contant.STATUS_ING);
        messageInfo.setNextTime(DateUtil.getNextTiem(new Date(), Contant.TIME_OUT));
        messageInfo.setCount(0);
        messageInfo.setCreateTime(new Date());
        messageInfo.setUpdateTime(new Date());
        messageInfoMapper.insertMessageInfo(messageInfo);


        orderSender.orderSender(order);
    }


}
