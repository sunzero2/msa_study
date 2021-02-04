package com.example.gamification.repository;

import com.example.gamification.domain.LeaderBoardRow;
import com.example.gamification.domain.ScoreCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoreCardRepository extends CrudRepository<ScoreCard, Long> {
    // JPQL(Java Persistence Query Language): 특정 데이터베이스 엔진을 기반으로 하는 쿼리가 아니라 코드를 기반으로 하는 일반적인 쿼리
    // 데이터베이스의 추상화를 유지하면서 쿼리를 사용할 수 있다는 큰 장점이 있다.
    // 대부분의 쿼리를 수행하기에 충분한 함수, 연산자, 표현식 등을 제공하지만 표준이 아니므로 일부 데이터베이스에서는 완전하게 지원하지 않는다.
    @Query("select sum(s.score)" + " from com.example.gamification.domain.ScoreCard s" + " WHERE s.userId = :userId GROUP BY s.userId")
    int getTotalScoreForUser(@Param("userId") final Long userId);

    @Query("select new com.example.gamification.domain.LeaderBoardRow(s.userId, sum(s.score)) from com.example.gamification.domain.ScoreCard s" +
            " GROUP BY s.userId order by sum(s.score) desc ")
    List<LeaderBoardRow> findFirst10();

    List<ScoreCard> findByUserIdOrderByScoreTimeStampDesc(final Long userId);
}
