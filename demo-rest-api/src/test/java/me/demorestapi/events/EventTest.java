package me.demorestapi.events;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

    @Test
    public void builder() {
        Event event = Event.builder()
                           .name("Inflearn Spring REST API")
                           .description("REST API development with with Spring")
                           .build();
        assertThat(event).isNotNull();
    }

    @Test
    void javaBean() {
        String name = "Event";
        String description = "Spring";

        Event event = new Event();
        event.setName("Event");
        event.setDescription("Spring");

        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);
    }
}