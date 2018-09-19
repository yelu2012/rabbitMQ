package com.uws.yl.send;

import com.uws.yl.bean.MessageInfo;
import com.uws.yl.bean.Order;
import com.uws.yl.contant.Contant;
import com.uws.yl.mapper.MessageInfoMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessageInfoMapper messageInfoMapper;

    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            if(ack){
                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setStatus(Contant.STATUS_SUC);
                messageInfo.setMessageId(correlationData.getId());
                messageInfoMapper.updateMessageInfo(messageInfo);
                System.out.println("消息发送成功");
            }else{
                System.out.println("消息发送失败");
            }
        }
    };


    public void orderSender(Order order){
        rabbitTemplate.setConfirmCallback(confirmCallback);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());
        rabbitTemplate.convertAndSend("order-exchange","order.abc" ,order, correlationData);
    }
}
