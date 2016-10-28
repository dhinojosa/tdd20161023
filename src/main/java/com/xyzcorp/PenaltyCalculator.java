package com.xyzcorp;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * Created by danno on 10/26/16.
 */
public class PenaltyCalculator {
    public static int calculate(LocalDate checkoutDate, LocalDate todaysDate, int amount) {
        int months = ((int) ChronoUnit.MONTHS.between(checkoutDate, todaysDate));
        if (months > 0 && checkoutDate.getDayOfMonth() == todaysDate.getDayOfMonth())
            months = months - 1;
        return months * amount;
    }
}
