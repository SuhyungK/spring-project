package me.demorestapi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

//    @MockBean
//    EventRepository eventRepository;

    @Test
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
               .andExpect(jsonPath("free").value(Matchers.not(true)));
    }

    @Test
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
}
