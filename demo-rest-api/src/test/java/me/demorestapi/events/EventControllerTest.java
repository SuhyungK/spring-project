package me.demorestapi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.demorestapi.common.TestDescription;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@DataJpaTest
public class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EventRepository eventRepository;

    @Test
    @TestDescription("정상적으로 이벤트를 생성하는 테스트")
    public void createEvent() throws Exception {
        EventDto eventDto = EventDto.builder()
                                    .name("이벤트 이름")
                                    .description("이벤트 내용")
                                    .beginEnrollmentDateTime(LocalDateTime.of(2024, 4, 25, 11, 31))
                                    .closeEnrollmentDateTime(LocalDateTime.of(2024, 4, 26, 11, 31))
                                    .beginEventDateTime(LocalDateTime.of(2024, 4, 28, 11, 31))
                                    .endEventDateTime(LocalDateTime.of(2024, 4, 29, 11, 31))
                                    .basePrice(100)
                                    .maxPrice(200)
                                    .location("강남역 투썸")
                                    .limitOfEnrollment(100)
                                    .build();


        System.out.println(objectMapper.writeValueAsString(eventDto));
        mockMvc.perform(post("/api/events")
                       .contentType(MediaType.APPLICATION_JSON_VALUE)
                       .accept(MediaType.APPLICATION_JSON_VALUE)
                       .content(objectMapper.writeValueAsString(eventDto))
               )
               .andDo(print())
               .andExpect(status().isCreated())
               .andExpect(jsonPath("id").exists())
               .andExpect(header().exists(HttpHeaders.LOCATION))
               .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
               .andExpect(jsonPath("id").value(Matchers.not(100)))
               .andExpect(jsonPath("free").value(false))
               .andExpect(jsonPath("offline").value(true))
               .andExpect(jsonPath("eventStatus").value(EventStatus.DRAFT.name()))
        ;
    }

    @Test
    @TestDescription("입력 받을 수 없는 값을 사용한 경우에 에러가 발생하는 테스트")
    public void createEvent_Bad_Request() throws Exception {
        Event event = Event.builder()
                           .id(100)
                           .name("Spring")
                           .description("REST API Development with Spring")
                           .beginEnrollmentDateTime(LocalDateTime.of(2024, 4, 25, 11, 31))
                           .closeEnrollmentDateTime(LocalDateTime.of(2024, 4, 26, 11, 31))
                           .beginEventDateTime(LocalDateTime.of(2024, 4, 28, 11, 31))
                           .endEventDateTime(LocalDateTime.of(2024, 4, 29, 11, 31))
                           .basePrice(100)
                           .maxPrice(200)
                           .limitOfEnrollment(100)
                           .location("역삼 멀티캠퍼스")
                           .limitOfEnrollment(100)
                           .free(true)
                           .offline(false)
                           .build();

        System.out.println(objectMapper.writeValueAsString(event));
        mockMvc.perform(post("/api/events")
                       .contentType(MediaType.APPLICATION_JSON_VALUE)
                       .accept(MediaType.APPLICATION_JSON_VALUE)
                       .content(objectMapper.writeValueAsString(event))
               )
               .andDo(print())
               .andExpect(status().isBadRequest());
    }

    @Test
    @TestDescription("입력 값이 비어 있는 경우 에러 발생 테스트")
    public void createEvent_Bad_Request_Empty_Input() throws Exception {
        EventDto eventDto = EventDto.builder()
                                    .build();

        this.mockMvc.perform(post("/api/events")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(this.objectMapper.writeValueAsString(eventDto)))
                    .andExpect(status().isBadRequest());
    }

    @Test
    @TestDescription("입력 값이 잘못된 경우 에러가 발생하는 테스트")
    public void createEvent_Bad_Request_Wrong_Input() throws Exception {
        EventDto eventDto = EventDto.builder()
                                    .name("이벤트 이름")
                                    .description("이벤트 내용")
                                    .beginEnrollmentDateTime(LocalDateTime.of(2024, 4, 26, 11, 31))
                                    .closeEnrollmentDateTime(LocalDateTime.of(2024, 4, 25, 11, 31))
                                    .beginEventDateTime(LocalDateTime.of(2024, 4, 29, 11, 31))
                                    .endEventDateTime(LocalDateTime.of(2024, 4, 28, 11, 31))
                                    .basePrice(10000)
                                    .maxPrice(200)
                                    .limitOfEnrollment(100)
                                    .location("강남역 D2 스타트업 스토리")
                                    .build();

        this.mockMvc.perform(post("/api/events")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(this.objectMapper.writeValueAsString(eventDto)))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$[0].objectName").exists())
                    .andExpect(jsonPath("$[0].defaultMessage").exists())
                    .andExpect(jsonPath("$[0].code").exists())
        ;
    }
}
