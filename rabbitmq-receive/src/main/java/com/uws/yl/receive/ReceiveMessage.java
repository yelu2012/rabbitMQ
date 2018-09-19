package com.uws.yl.receive;

import com.rabbitmq.client.Channel;
import com.uws.yl.bean.MessageInfo;
import com.uws.yl.bean.Order;
import com.uws.yl.contant.Contant;
import com.uws.yl.service.MessageInfoService;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class ReceiveMessage {
    @Autowired
    private MessageInfoService messageInfoService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order-queue",durable = "true"),
            exchange = @Exchange(value = "order-exchange",durable = "true",type = "topic"),
            key = "order.#"
    ))
    @RabbitHandler
    public void receiveMessage(@Payload Order order , Channel channel,@Headers Map<String,Object> headers) throws IOException {
        System.out.println(order.getMessageId());
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setStatus(Contant.STATUS_OK);
        messageInfo.setMessageId(order.getMessageId());
        messageInfoService.updateMessageInfo(messageInfo);
        channel.basicAck((Long)headers.get(AmqpHeaders.DELIVERY_TAG), false);
    }
}
