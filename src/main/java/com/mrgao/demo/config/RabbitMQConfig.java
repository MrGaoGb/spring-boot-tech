package com.mrgao.demo.config;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMQ配置
 *
 * @author hanyl
 */
@Configuration
@Slf4j
@Getter
public class RabbitMQConfig {

    /**
     * 业务队列-名称
     */
    @Value("${mq.queueBinding.bussinessQueue.noActiveQueueName}")
    private String bisQueueName;
    /**
     * 业务队列-交换机名称
     */
    @Value("${mq.queueBinding.bussinessQueue.noActiveExchangeName}")
    private String bisExchangeName;
    /**
     * 业务队列-路由键
     */
    @Value("${mq.queueBinding.bussinessQueue.noActiveRoutingKey}")
    private String bisNoActiveRoutingKey;


    /**
     * 死信队列-名称
     */
    @Value("${mq.queueBinding.dlQueue.dlQueueName}")
    private String dlQueueName;
    /**
     * 死信队列-交换机名称
     */
    @Value("${mq.queueBinding.dlQueue.dlExchangeName}")
    private String dlExchangeName;
    /**
     * 死信队列-路由键
     */
    @Value("${mq.queueBinding.dlQueue.dlRoutingKey}")
    private String dlRoutingKey;

    /**
     * 新增rabbitMQ的return 机制 和 confirm 机制
     *
     * @return
     * @author VilderLee
     */
    //@Bean
    //public RabbitTemplate rabbitTemplate(CachingConnectionFactory rabbitConnectionFactory) {
    //    RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitConnectionFactory);
    //    //确保消息发送到exchange中
    //    rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
    //        if (ack) {
    //            log.info("消息成功发送到Exchange");
    //        } else {
    //            log.info("消息发送到Exchange失败, {}, cause: {}", correlationData, cause);
    //        }
    //    });
    //
    //    //确保消息路由到queue中 setMandatory表示强制执行
    //    rabbitTemplate.setMandatory(true);
    //    rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
    //        log.error("当前消息没有投递到Queue中！消息{}", JSONObject.toJSONString(message.getBody()));
    //        log.error("错误码为{}", replyCode);
    //        log.error("错误描述为{}", replyText);
    //        log.error("交换机为{}", exchange);
    //        log.error("路由Key为{}", routingKey);
    //
    //    });
    //
    //    return rabbitTemplate;
    //}

    /**
     * ==================================构建死信队列======================================
     */
    //死信队列
    //@Bean
    //public Queue dlQueue() {
    //    return new Queue(dlQueueName, true);
    //}
    //
    ////死信交换机
    //@Bean
    //public DirectExchange dlExchange() {
    //    return new DirectExchange(dlExchangeName);
    //}
    //
    ////死信队列与死信交换机进行绑定
    //@Bean
    //public Binding dlBindQueueAndExchange() {
    //    return BindingBuilder.bind(dlQueue()).to(dlExchange()).with(dlRoutingKey);
    //}

    private final String dle = "x-dead-letter-exchange";
    private final String dlk = "x-dead-letter-routing-key";
    private final String ttl = "x-message-ttl";

    /**
     * ==================================构建业务队列======================================
     */
    //创建业务队列
    //@Bean
    //public Queue bisNoActiveQueue() {
    //    Map<String, Object> params = new HashMap<>(4);
    //    //设置队列过期时间 10s 采用TTL方式：设置队列过期时间实现延时消费
    //    params.put(ttl, 10000);
    //    //声明当前队列绑定的死信交换机
    //    params.put(dle, dlExchangeName);
    //    //声明当前队列的死信路由键
    //    params.put(dlk, dlRoutingKey);
    //    return QueueBuilder.durable(bisQueueName).withArguments(params).build();
    //}

    //创建业务交换机
    //@Bean
    //public DirectExchange bisNoActiveExchange() {
    //    return new DirectExchange(bisExchangeName);
    //}

    //业务队列与业务交换机进行绑定
    //@Bean
    //public Binding bisBindNoActiveQueueAndExchange() {
    //    return BindingBuilder.bind(bisNoActiveQueue()).to(bisNoActiveExchange()).with(bisNoActiveRoutingKey);
    //}
}
