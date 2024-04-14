package org.example.unittesting.business;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class SomeBusinessImplTest {

    @Test
    public void calculateSum_basic() {
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        int actualResult = someBusiness.calculateSum(new int[]{1, 2, 3});
        int expectedResult = 6;

//        assertEquals(expectedResult, actualResult);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void calculateSum_empty() {
        SomeBusinessImpl someBusiness = new SomeBusinessImpl();
        int actualResult = someBusiness.calculateSum(new int[]{});
        int expectedResult = 0;

        assertThat(actualResult).isEqualTo(expectedResult);
    }

}