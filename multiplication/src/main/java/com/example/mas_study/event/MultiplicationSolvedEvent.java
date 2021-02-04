package com.example.mas_study.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

// 이벤트 객체는 커지면 좋지 않다.
// 객체의 변화하는 데이터를 이벤트에 포함시키는 것은 위험하다.
// 이보다는 사용자 정보가 수정될 때마다 식별자를 알리고 메시지를 소비하는 쪽에서 로직을 처리할 때 최신 상태를 요청하게 하는 것이 나을 수도 있다.
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class MultiplicationSolvedEvent {
    private final Long multiplicationResultAttemptId;
    private final Long userId;
    private final boolean correct;
}
