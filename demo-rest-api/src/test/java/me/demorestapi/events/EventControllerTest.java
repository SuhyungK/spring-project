package me.demorestapi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void createEvent() throws Exception {
        EventDto event = EventDto.builder()
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
               .andExpect(jsonPath("id").exists())
               .andExpect(header().exists(HttpHeaders.LOCATION))
               .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
               .andExpect(jsonPath("id").value(Matchers.not(100)))
               .andExpect(jsonPath("free").value(Matchers.not(true)))
               .andExpect(jsonPath("eventStatus").value(EventStatus.DRAFT.name()));
        ;
    }

    @Test
    public void createEvent_Bad_Request() throws Exception {
        Event event = Event.builder()
                           .id(100)
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
                           .free(true)
                           .offline(false)
                           .eventStatus(EventStatus.PUBLISHED)
                           .build();

        // perform 안에 요청을 주면 응답이 나옴
        mockMvc.perform(post("/api/events/")
                       .contentType(MediaType.APPLICATION_JSON_VALUE)
                       .accept(MediaTypes.HAL_JSON)
                       .content(objectMapper.writeValueAsString(event))
               )
               .andDo(print())
               .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void createEvent_Bad_Request_Empty_Input() throws Exception {
        EventDto eventDto = EventDto.builder()
                                    .build();

        this.mockMvc.perform(post("/api/events/")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(this.objectMapper.writeValueAsString(eventDto)))
                    .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void createEvent_Bad_Request_Wrong_Input() throws Exception {
        EventDto event = EventDto.builder()
                                 .name("Spring")
                                 .description("REST API Development with Spring")
                                 .beginEnrollmentDateTime(LocalDateTime.of(2024, 1, 23, 14, 21))
                                 .closeEnrollmentDateTime(LocalDateTime.of(2024, 1, 24, 14, 21))
                                 .beginEventDateTime(LocalDateTime.of(2024, 1, 25, 14, 16))
                                 .endEventDateTime(LocalDateTime.of(2024, 1, 24, 14, 16))
                                 .basePrice(10000)
                                 .maxPrice(200)
                                 .limitOfEnrollment(100)
                                 .location("강남역 D2 스타트업 팩토리")
                                 .build();

        this.mockMvc.perform(post("/api/events/")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(this.objectMapper.writeValueAsString(event)))
                    .andExpect(status().isBadRequest())
        ;
    }


}