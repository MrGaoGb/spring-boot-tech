package com.mrgao.demo.config.rabbit;

import com.mrgao.demo.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: Mr.Gao
 * @date: 2022年03月09日 14:47
 * @description:生产者
 */
@Slf4j
@Component
public class RabbitSender {

    @Autowired
    private RabbitMQConfig rabbitMQConfig;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send (String msg) {
        log.info (">>延时队列<<[{}] begin sendMsg = {}", LocalDateTime.now ().format (DateTimeFormatter.ofPattern ("yyyy-MM-dd HH:mm:ss.SSS")), msg);

        //方式1 配合方式TTL方式：设置 队列 过期时间实现延时消费
        rabbitTemplate.convertAndSend (rabbitMQConfig.getBisExchangeName (), rabbitMQConfig.getBisNoActiveRoutingKey (), msg);
        //方式2 采用TTL方式：设置 消息 过期时间实现延时消费
//        rabbitTemplate.convertAndSend(rabbitMQConfig.getBisExchangeName(), rabbitMQConfig.getBisNoActiveRoutingKey(), msg, (message -> {
//            MessageProperties properties = message.getMessageProperties();
//            //延时5s
//            properties.setExpiration("10000");
//            return message;
//        }));
        log.info (">>延时队列<<[{}] end~~~~", LocalDateTime.now ().format (DateTimeFormatter.ofPattern ("yyyy-MM-dd HH:mm:ss.SSS")));
    }
}
