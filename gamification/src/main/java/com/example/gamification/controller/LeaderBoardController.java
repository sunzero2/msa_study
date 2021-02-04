package com.example.gamification.controller;

import com.example.gamification.domain.LeaderBoardRow;
import com.example.gamification.service.LeaderBoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 현재 리더보드를 조회하는 엔드포인트 노출
@RestController
@RequestMapping("/leaders")
public class LeaderBoardController {
    private final LeaderBoardService leaderBoardService;

    public LeaderBoardController(final LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }

    @GetMapping
    public List<LeaderBoardRow> getLeaderBoard() {
        return leaderBoardService.getCurrentLeaderBoard();
    }
}
