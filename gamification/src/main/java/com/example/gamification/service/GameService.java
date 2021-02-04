package com.example.gamification.service;

import com.example.gamification.domain.GameStats;

public interface GameService {
    // 사용자가 제출한 답안을 처리
    GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct);

    // 사용자의 게임 통계를 조회
    GameStats retrieveStatsForUser(Long userId);
}
