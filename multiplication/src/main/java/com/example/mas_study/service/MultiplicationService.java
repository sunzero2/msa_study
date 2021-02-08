package com.example.mas_study.service;

import com.example.mas_study.domain.Multiplication;
import com.example.mas_study.domain.MultiplicationResultAttempt;

import java.util.List;
import java.util.Optional;

public interface MultiplicationService {
    Multiplication createRandomMultiplication();
    boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);
    List<MultiplicationResultAttempt> getStatsForUser(String userAlias);
    Optional<MultiplicationResultAttempt> getResultById(Long resultId);
}
