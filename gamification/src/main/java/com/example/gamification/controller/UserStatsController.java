package com.example.gamification.controller;

import com.example.gamification.domain.GameStats;
import com.example.gamification.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// GameStats 객체를 JSON으로 표현한 결과를 엔드포인트로 노출
@RestController
@RequestMapping("/stats")
public class UserStatsController {
    private final GameService gameService;

    public UserStatsController(final GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public GameStats getStatsForUser(@RequestParam("userId") final Long userId) {
        return gameService.retrieveStatsForUser(userId);
    }
}
