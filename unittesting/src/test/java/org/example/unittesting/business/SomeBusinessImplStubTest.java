package org.example.unittesting.business;

import org.example.unittesting.data.SomeDataService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SomeDataServiceStub implements SomeDataService {

    @Override
    public int[] retrieveAllData() {
        return new int[] {1, 2, 3};
    }
}

class SomeDataServiceEmptyStub implements SomeDataService {

    @Override
    public int[] retrieveAllData() {
        return new int[] {};
    }
}


class SomeBusinessImplStubTest {

    @Test
    public void calculateSumUsingDataService_basic() {
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        someBusiness.setSomeDataService(new SomeDataServiceStub());
        int actualResult = someBusiness.calculateSumUsingDataService();
        int expectedResult = 6;

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void calculateSumUsingDataService_empty() {
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        someBusiness.setSomeDataService(new SomeDataServiceEmptyStub());
        int actualResult = someBusiness.calculateSumUsingDataService();
        int expectedResult = 0;

        assertThat(actualResult).isEqualTo(expectedResult);
    }

}