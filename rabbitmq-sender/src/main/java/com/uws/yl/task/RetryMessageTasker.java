package com.uws.yl.task;

import com.uws.yl.bean.MessageInfo;
import com.uws.yl.bean.Order;
import com.uws.yl.contant.Contant;
import com.uws.yl.mapper.MessageInfoMapper;
import com.uws.yl.send.OrderSender;
import com.uws.yl.util.FastJsonConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetryMessageTasker {
    @Autowired
    private OrderSender orderSender;
    @Autowired
    private MessageInfoMapper messageInfoMapper;


    @Scheduled(initialDelay = 50000,fixedDelay = 10000)
    public void reSend(){
        List<MessageInfo> messageInfoList = messageInfoMapper.getListMessageInfo();
        messageInfoList.forEach(messageInfo -> {
            System.out.println(messageInfo.getCount()+"------------------");
            if(messageInfo.getCount()>3){
                messageInfo.setStatus(Contant.STATUS_FAIL);
                messageInfoMapper.updateMessageInfo(messageInfo);
            }else{
                Order order =  FastJsonConvertUtil.jsonStringToObject(messageInfo.getMessage(),Order.class);
                int count = messageInfo.getCount();
                messageInfo.setCount(count+1);
                messageInfoMapper.updataMessageSendConut(messageInfo);
                orderSender.orderSender(order);
            }
        });
    }
}
