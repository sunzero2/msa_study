package com.example.mas_study.service;

import com.example.mas_study.domain.Multiplication;
import com.example.mas_study.domain.MultiplicationResultAttempt;

import java.util.List;

public interface MultiplicationService {
    Multiplication createRandomMultiplication();
    boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);
    List<MultiplicationResultAttempt> getStatsForUser(String userAlias);
}
