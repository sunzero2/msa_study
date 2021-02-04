package com.example.gamification.event;

import com.example.gamification.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventHandler {
    private GameService gameService;

    EventHandler(final GameService gameService) {
        this.gameService = gameService;
    }

    // 이벤트를 소비하는 메서드
    // RabbitHandler: 브로커가 보낸 메시지를 우리가 정의한 큐를 통해 받는 복잡한 과정을 처리함
    // 애너테이션 파라미터로 큐 이름을 전달해야 함
    @RabbitListener(queues = "${multiplication.queue}")
    void handleMultiplicationSolved(final MultiplicationSolvedEvent event) {
        log.info("Multiplication Solved Event 수신: {}", event.getMultiplicationResultAttemptId());
        try {
            gameService.newAttemptForUser(event.getUserId(), event.getMultiplicationResultAttemptId(), event.isCorrect());
        } catch (final Exception e) {
            log.error("MultiplicationSolvedEvent 처리 시 에러", e);

            // 해당 이벤트가 다시 큐로 들어가거나 두 번 처리되지 않도록 예외 발생시킴
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
