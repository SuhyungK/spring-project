package org.example.unittesting.business;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListMockTest {

    List mock = mock(List.class);
    @Test
    void size_basic() {
        when(mock.size()).thenReturn(5);
        assertThat(mock.size())
                  .isEqualTo(5);
    }

    @Test
    void returnDifferentValues() {
        when(mock.size()).thenReturn(5)
                         .thenReturn(10);

        assertThat(mock.size())
                  .isEqualTo(5);

        assertThat(mock.size())
                  .isEqualTo(10);
    }

    @Test
    void returnWithParameters() {
        when(mock.get(0)).thenReturn("in28Minutes");
        assertThat(mock.get(0)).isEqualTo("in28Minutes");
        assertThat(mock.get(1)).isNull();
    }

    @Test
    void retrunWithGenericParameters() {
        when(mock.get(anyInt())).thenReturn("in28Minutes");
        assertThat(mock.get(0)).isEqualTo("in28Minutes");
        assertThat(mock.get(1)).isEqualTo("in28Minutes");
    }
}

