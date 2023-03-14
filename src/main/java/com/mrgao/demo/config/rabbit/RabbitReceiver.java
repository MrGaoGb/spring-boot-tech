package com.mrgao.demo.config.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author: Mr.Gao
 * @date: 2022年03月09日 14:50
 * @description:消费者
 */
@Slf4j
@Component
public class RabbitReceiver {

    /**
     * (监听)消费死信队列的消息
     *
     * @param message
     */
    @RabbitHandler
    @RabbitListener(queues = {"${mq.queueBinding.dlQueue.dlQueueName}"})
    public void receiveMessage (String message) {
        log.info (">>延时队列<<[{}] >> RabbitReceiver 收到信息:{} ", LocalDateTime.now ().format (DateTimeFormatter.ofPattern ("yyyy-MM-dd HH:mm:ss.SSS")), message);
        try {
            TimeUnit.SECONDS.sleep (2);
            log.info (">>延时队列<<  业务处理~~~~");
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        log.info (">>延时队列<< RabbitReceiver >> END Thanks♪(･ω･)ﾉ");
    }
}
