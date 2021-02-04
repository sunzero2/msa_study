package com.example.gamification.service.impl;

import com.example.gamification.domain.LeaderBoardRow;
import com.example.gamification.repository.ScoreCardRepository;
import com.example.gamification.service.LeaderBoardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {
    private ScoreCardRepository scoreCardRepository;

    LeaderBoardServiceImpl(final ScoreCardRepository scoreCardRepository) {
        this.scoreCardRepository = scoreCardRepository;
    }

    @Override
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        return scoreCardRepository.findFirst10();
    }
}
