package org.example.unittesting.business;

import org.example.unittesting.data.SomeDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SomeBusinessImplMockTestV2 {

    @InjectMocks
    SomeBusinessImpl someBusiness;

    @Mock
    SomeDataService dataServiceMock;

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