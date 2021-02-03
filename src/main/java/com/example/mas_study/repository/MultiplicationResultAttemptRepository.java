package com.example.mas_study.repository;

import com.example.mas_study.domain.MultiplicationResultAttempt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// CrudRepository: 자바 제네릭을 사용하여 저장하려는 클래스와 식별자 타입을 인자로 넘겨줘야 함
// PagingAndSortingRepository: CRUD 기능에 페이지와 정렬 기능까지 있는 유용한 인터페이스
public interface MultiplicationResultAttemptRepository extends CrudRepository<MultiplicationResultAttempt, Long> {
    List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}
