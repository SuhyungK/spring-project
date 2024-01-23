package me.demorestapi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EventControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void createEvent() throws Exception {
        Event event = Event.builder()
                           .name("Spring")
                           .description("REST API Development with Spring")
                           .beginEnrollmentDateTime(LocalDateTime.of(2024, 1, 23, 14, 21))
                           .closeEnrollmentDateTime(LocalDateTime.of(2024, 1, 24, 14, 21))
                           .beginEventDateTime(LocalDateTime.of(2024, 1, 25, 14, 16))
                           .endEventDateTime(LocalDateTime.of(2024, 1, 26, 14, 16))
                           .basePrice(100)
                           .maxPrice(200)
                           .limitOfEnrollment(100)
                           .location("강남역 D2 스타트업 팩토리")
                           .build();

        // perform 안에 요청을 주면 응답이 나옴
        mockMvc.perform(post("/api/events/")
                       .contentType(MediaType.APPLICATION_JSON_VALUE)
                       .accept(MediaTypes.HAL_JSON)
                       .content(objectMapper.writeValueAsString(event))
               )
               .andDo(print())
               .andExpect(status().isCreated())
               .andExpect(jsonPath("id").exists());
    }
}