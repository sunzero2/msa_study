package com.example.mas_study.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

// SpringRunner: 애플리케이션 컨텍스트를 초기화하고 필요한 객체를 주입
// 컨텍스트는 캐시로 재사용할 수 있어서 테스트 당 한 번만 로딩
@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomGeneratorServiceTest {
    @Autowired
    private RandomGeneratorService randomGeneratorService;

    @Test
    public void generateRandomFactorIsBetweenExpectedLimits() throws Exception {
        List<Integer> randomFactors = IntStream.range(0, 1000)
                .map(i -> randomGeneratorService.generateRandomFactor())
                .boxed()
                .collect(Collectors.toList());

        assertThat(randomFactors)
                .containsOnlyElementsOf(IntStream.range(11, 100)
                .boxed()
                .collect(Collectors.toList()));
    }
}
