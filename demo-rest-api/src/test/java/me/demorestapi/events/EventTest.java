package me.demorestapi.events;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class EventTest {

    @Test
    public void builder() {
        Event event = Event.builder()
                           .build();

        assertThat(event)
                .isNotNull();
    }

    @Test
    public void javaBean() {
        // Given
        String name = "Event";
        String description = "Spring";

        // When
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);

        // Then
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);
    }

    @Test
    @Parameters
    public void testFree(int basePrice, int maxPrice, boolean isFree) {
        Event event = Event.builder()
                           .basePrice(basePrice)
                           .maxPrice(maxPrice)
                           .build();

        event.update();

        assertThat(event.isFree()).isEqualTo(isFree);
    }

    private Object[] parametersForTestFree() {
        return new Object[]{
                new Object[]{0, 0, true},
                new Object[]{100, 0, false},
                new Object[]{0, 1, false},
                new Object[]{100, 200, false}
        };
    }

    @Test
    @Parameters({
            "강남역 투썸 플레이스, true",
            ",false",
            "           , false"
    })
    public void testOffline(String location, boolean isOffline) {
        Event event = Event.builder()
                           .location(location)
                           .build();

        event.update();

        assertThat(event.isOffline()).isEqualTo(isOffline);
    }

//    private Object[] parametersForTestOffline() {
//        return new Object[]{
//                new Object[]{"강남역 투썸 플레이스", true},
//                new Object[]{null, false},
//                new Object[]{"      ", false}
//        };
//    }
}