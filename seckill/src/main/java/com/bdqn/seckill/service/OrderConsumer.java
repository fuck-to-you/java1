package com.bdqn.seckill.service;

import com.bdqn.seckill.dao.OrderDAO;
import com.bdqn.seckill.entity.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @Author ldwjava
 * @Date 2019/7/17 9:34
 * @Desc TODO
 **/

@Component
public class OrderConsumer {

    @Autowired
    private OrderDAO orderDAO;

    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(name = "seckllexchange", type = "fanout"),
                    value = @Queue(name = "seckillqueue", autoDelete = "false", durable = "true", exclusive = "false"),
                    key = "#"
            )
    )
    @RabbitHandler
    public void handleMsg(@Payload Map data, Channel channel, @Headers Map<String, Object> headers) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //创建订单保存数据库
        Order order = new Order();
        order.setOrderNo(data.get("orderNO").toString());
        order.setOrderStatus(0);
        order.setUserid(data.get("uid").toString());
        order.setRecvName("xxx");
        order.setRecvMobile("xxx");
        order.setRecvAddress("xxxxx");
        order.setAmout(18.9f);
        order.setPostage(0f);
        order.setCreateTime(new Date());
        orderDAO.insert(order);

        System.out.println(order.getOrderId() + "======");

        //手动确认签收消息
        Long tagId = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            channel.basicAck(tagId, false);
            System.out.println("订单消费成功,数据库添加数据成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
