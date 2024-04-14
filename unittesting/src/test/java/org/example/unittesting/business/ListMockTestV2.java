package org.example.unittesting.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ListMockTestV2 {

    List<String> mock = mock(List.class);

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

    @Test
    void verificationBasics() {
        String value = mock.get(0);
        String value2 = mock.get(1);

        // 메서드 실행 여부 검증
        verify(mock).get(0);
        verify(mock, times(2)).get(anyInt());
        verify(mock, atLeast(1)).get(anyInt());
        verify(mock, atMost(2)).get(2);
        verify(mock, atLeastOnce()).get(anyInt());
    }

    @Test
    void argumentCapturing() {
        mock.add("SomeString");

        // Verification
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mock).add(captor.capture());

        assertThat("SomeString").isEqualTo(captor.getValue());
    }

    @Test
    void multipleArgumentCapturing() {
        mock.add("SomeString1");
        mock.add("SomeString2");

        // Verification
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mock, times(2)).add(captor.capture());

        List<String> allValues = captor.getAllValues();
        assertThat("SomeString1").isEqualTo(allValues.get(0));
        assertThat("SomeString2").isEqualTo(allValues.get(1));
    }

    @Test
    void mocking() {
        // instead of real action
        ArrayList arrayListMock = mock(ArrayList.class);

        arrayListMock.get(0); // null
        arrayListMock.size(); // 0
        arrayListMock.add("Test"); // 실제 객체가 아니기 때문에 값이 실제로 추가되지는 않는다
        arrayListMock.add("Test2");
        arrayListMock.size(); // 0 실제로 값이 추가된 것이 아니기 때문에 size는 그대로 0
        when(arrayListMock.size()).thenReturn(5);
        System.out.println(arrayListMock.size()); // 5 size() 메서드 실행시 5가 나오도록 했으므로
    }


    @Test
    void spying() {
        // in addition to the real world action
        ArrayList arrayListSpyt = spy(ArrayList.class);

        arrayListSpyt.add("Test0");
        arrayListSpyt.get(0); // Test0
        arrayListSpyt.size(); // 1
        arrayListSpyt.add("Test");
        arrayListSpyt.add("Test4");
        arrayListSpyt.size(); // 3 실제로 값이 추가되기 떄문에 3
        System.out.println(arrayListSpyt.size());

        when(arrayListSpyt.size()).thenReturn(5);
        System.out.println(arrayListSpyt.size()); // 5 size() 메서드 실행시 5가 나오도록 설정했으므로 이제는 5가 나옴

        verify(arrayListSpyt).add("Test4");
    }
}

