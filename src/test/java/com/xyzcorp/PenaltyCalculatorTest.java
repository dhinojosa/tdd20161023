package com.xyzcorp;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PenaltyCalculatorTest {
    @Test
    public void testCalculatePenaltyCheckoutSameDate() throws Exception {
        int result = PenaltyCalculator
                .calculate(LocalDate.of(2016, 10, 30), LocalDate.of(2016,10,30), 10);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testCalculatePenaltyCheckoutOneMonth() throws Exception {
        int result = PenaltyCalculator
                .calculate(LocalDate.of(2016, 10, 30), LocalDate.of(2016,11,30), 10);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testCalculatePenaltyCheckoutOneMonthOneDay() throws Exception {
        int result = PenaltyCalculator
                .calculate(LocalDate.of(2016, 10, 30), LocalDate.of(2016,12,1), 10);
        assertThat(result).isEqualTo(10);
    }

    @Test
    public void testCalculatePenaltyCheckoutOneYear() throws Exception {
        int result = PenaltyCalculator
                .calculate(LocalDate.of(2016, 10, 30), LocalDate.of(2017,10,30), 10);
        assertThat(result).isEqualTo(110);
    }


    @Test
    public void testCalculatePenaltyCheckoutOneYearOneDay() throws Exception {
        int result = PenaltyCalculator
                .calculate(LocalDate.of(2016, 10, 30), LocalDate.of(2017,10,31), 10);
        assertThat(result).isEqualTo(120);
    }


    @Test
    public void testCalculatePenaltyCheckoutFebruaryOneMonthLeapYear() throws Exception {
        int result = PenaltyCalculator
                .calculate(LocalDate.of(2016, 1, 28), LocalDate.of(2016,2,28), 10);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testCalculatePenaltyCheckoutFebruaryOneMonthOnLeapYear() throws Exception {
        int result = PenaltyCalculator
                .calculate(LocalDate.of(2016, 1, 29), LocalDate.of(2016,2,29), 10);
        assertThat(result).isEqualTo(0);
    }
}
