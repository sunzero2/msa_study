package com.example.mas_study.controller;

import com.example.mas_study.domain.MultiplicationResultAttempt;
import com.example.mas_study.service.MultiplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/results")
public class MultiplicationResultAttemptController {
    private final MultiplicationService multiplicationService;
    private final int serverPort;

    @Autowired
    MultiplicationResultAttemptController(final MultiplicationService multiplicationService, @Value("${server.port}") int serverPort) {
        this.multiplicationService = multiplicationService;
        this.serverPort = serverPort;
    }

    @PostMapping
    ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt multiplicationResultAttempt) {
        boolean isCorrect = multiplicationService.checkAttempt(multiplicationResultAttempt);
        MultiplicationResultAttempt attemptCopy = new MultiplicationResultAttempt(
                multiplicationResultAttempt.getUser(),
                multiplicationResultAttempt.getMultiplication(),
                multiplicationResultAttempt.getResultAttempt(),
                isCorrect
        );
        return ResponseEntity.ok(attemptCopy);
    }

    @GetMapping
    ResponseEntity<List<MultiplicationResultAttempt>> getStatistics(@RequestParam("alias") String alias) {
        return ResponseEntity.ok(multiplicationService.getStatsForUser(alias));
    }

    @GetMapping("/{resultId}")
    ResponseEntity getResultById(final @PathVariable("resultId") Long resultId) {
        log.info("조회 결과 {}를 가져온 서버 @ {}", resultId, serverPort);

        return ResponseEntity.ok(
                multiplicationService.getResultById(resultId)
        );
    }
}
