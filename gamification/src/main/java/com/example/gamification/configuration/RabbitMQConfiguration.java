package com.example.gamification.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

// 처음 세 개의 메서드는 새로운 큐와 TopicExchange를 함께 바인딩해서 연결
// 마지막 세 개의 메서드는 구독자 쪽 JSON 역직렬화를 설정. 곱셈 서비스의 RabbitMQConfiguration과 다르게 동작
@Configuration
public class RabbitMQConfiguration implements RabbitListenerConfigurer {

    // multiplication.exchange: 곱셈 서비스에서 정의된 것과 같음
    @Bean
    public TopicExchange multiplicationExchange(@Value("${multiplication.exchange}") final String exchangeName) {
        return new TopicExchange(exchangeName);
    }

    // true로 설정하면 큐를 내구성 있게 만든다. -> 브로커가 중지된 후에도 대기 중인 이벤트를 처리할 수 있다.
    @Bean
    public Queue gamificationMultiplicationQueue(@Value("${multiplication.queue}") final String queueName) {
        return new Queue(queueName, true);
    }

    @Bean
    Binding binding(final Queue queue, final TopicExchange exchange, @Value("${multiplication.anything.routing-key}") final String routingKey) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
        rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
}
