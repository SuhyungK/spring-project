package org.example.unittesting.business;

import org.example.unittesting.data.SomeDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SomeBusinessImplMockTest {

    SomeBusinessImpl someBusiness = new SomeBusinessImpl();
    SomeDataService dataServiceMock = mock(SomeDataService.class);

    @BeforeEach
    void before() {
        someBusiness.setSomeDataService(dataServiceMock);
    }

    @Test
    public void calculateSumUsingDataService_basic() {
        // Mock 객체가 어떤 역할을 했으면 하는지 정의할 수 있음
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {1, 2, 3});
        assertThat(someBusiness.calculateSumUsingDataService()).isEqualTo(6);
    }

    @Test
    public void calculateSumUsingDataService_empty() {
        // Mock 객체가 어떤 역할을 했으면 하는지 정의할 수 있음
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});
        assertThat(someBusiness.calculateSumUsingDataService()).isEqualTo(0);
    }
}