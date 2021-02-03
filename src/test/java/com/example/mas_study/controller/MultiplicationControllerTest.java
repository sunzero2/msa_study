package com.example.mas_study.controller;

import com.example.mas_study.domain.Multiplication;
import com.example.mas_study.service.MultiplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
// WebMvcTest: 스프링의 웹 애플리케이션 컨텍스트를 초기화함
// SpringBootTest처럼 모든 설정을 불러오는 것이 아니라 MVC 레이어와 관련된 설정만 불러옴. MockMvc 빈도 불러옴
@WebMvcTest(MultiplicationController.class)
public class MultiplicationControllerTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;

    // JacksonTester: 자동으로 설정할 수 있고 @JsonTest 애노테이션을 이용해 자동으로 주입할 수 있음
    // @WebMvcTest를 사용하면 수동으로 설정해야 함
    private JacksonTester<Multiplication> json;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getRandomMultiplicationTest() throws Exception {
        given(multiplicationService.createRandomMultiplication())
                .willReturn(new Multiplication(70, 20));

        MockHttpServletResponse response = mvc.perform(
                get("/multiplications/random")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(json.write(new Multiplication(70, 20)).getJson());
    }
}

