package com.example.mas_study.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// 애플리케이션에서 곱셈을 나타내는 클래스(a*b)
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
// Entity: JPA 저장소에 저장할 수 있는 JPA의 개체임을 명시. JPA에서 리플렉션을 통해 객체를 인스턴스화할 때 필요한 빈 생성자도 있음
@Entity
public final class Multiplication {
    // Id: 기본키를 의미, Long을 활용해야 함
    @Id
    // GeneratedValue: 따로 값을 지정하지 않아도 자동으로 생성되는 값
    @GeneratedValue
    // Column: JPA 대신 명시적으로 컬럼명 지정. 두 테이블을 조인할 때 고정된 컬럼명이 유용함
    @Column(name="MULTIPLICATION_ID")
    private Long id;
    private final int factorA;
    private final int factorB;

    Multiplication() {
        this(0, 0);
    }
}
