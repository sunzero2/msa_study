package com.example.mas_study.service;

import com.example.mas_study.domain.Multiplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiplicationServiceTest {

    // MockBean: 스프링이 RandomGeneratorService 인터페이스에 맞는 구현 클래스를 찾아서 주입해주는 대신 목(mock) 객체를 주입
    // 목 객체: 실제 모듈과 비슷하게 동작하도록 정의한 가짜 객체. 진짜 객체가 아직 구현되기 전이나 진짜 객체로 테스트하기 어려운 경우 목 객체로 테스트를 작성할 수 있음
    @MockBean
    private RandomGeneratorService randomGeneratorService;

    @Autowired
    private MultiplicationService multiplicationService;

    @Test
    public void createRandomMultiplicationTest() {
        // RandomGeneratorService를 호출할 때 어떤 값을 반환할지 미리 정의하고 있음
        // -> 행위 주도 개발(behavior-driven development, BDD) 방식으로 테스트를 더 읽기 쉽게 해주고 요구사항을 정의하는데 도움을 줌
        // BDD: TDD에서 파생된 개발 방법. 테스트 작성 시 사용자가 원하는 행위에 중점을 둠 -> 팀원 또는 고객과의 의사소통 과정에서 모호한 내용을 줄일 수 있음
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);
        Multiplication multiplication = multiplicationService.createRandomMultiplication();

        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
//        assertThat(multiplication.getResult()).isEqualTo(1500);
    }
}
