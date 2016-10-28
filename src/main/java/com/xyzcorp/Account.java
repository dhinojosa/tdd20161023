package com.xyzcorp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Created by danno on 10/26/16.
 */
public class Account {
    private final String name;
    private final String title;
    private LocalDate checkoutDate;

    public Account(String name, String title, LocalDate checkoutDate) {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(checkoutDate, "Checkout date cannot be null");
        this.name = name;
        this.title = title;
        this.checkoutDate = checkoutDate;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    @Override
    public String toString() {
        return String.format("%s{%s, %s, %s}",
                getClass().getSimpleName(), name, title, checkoutDate.toString());
    }
}
