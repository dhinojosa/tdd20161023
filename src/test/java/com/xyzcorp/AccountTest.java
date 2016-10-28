package com.xyzcorp;


import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {


    @Test
    public void testName() throws Exception {
        Account account = new Account
                ("Peter Pavlov", "Joy of C#", LocalDate.of(2015, 3, 10));
        assertThat(account.getName()).isEqualTo("Peter Pavlov");
    }


    @Test
    public void testDifferentName() throws Exception {
        Account account = new Account
                ("Petar Kirov", "Joy of C++", LocalDate.of(2016, 1, 10));
        assertThat(account.getName()).isEqualTo("Petar Kirov");
    }

    @Test
    public void testTitle() throws Exception {
        Account account = new Account
                ("Peter Pavlov", "Joy of C#", LocalDate.of(2015, 3, 10));
        assertThat(account.getTitle()).isEqualTo("Joy of C#");
    }

    @Test
    public void testDifferentTitle() throws Exception {
        Account account = new Account
                ("Petar Kirov", "Joy of C++", LocalDate.of(2016, 1, 10));
        assertThat(account.getTitle()).isEqualTo("Joy of C++");
    }

    @Test
    public void testCheckoutDate() throws Exception {
        Account account = new Account
                ("Peter Pavlov", "Joy of C#", LocalDate.of(2015, 3, 10));
        assertThat(account.getCheckoutDate()).isEqualTo(LocalDate.of(2015, 3, 10));
    }

    @Test
    public void testDifferentCheckoutDate() throws Exception {
        Account account = new Account
                ("Petar Kirov", "Joy of C++", LocalDate.of(2016, 1, 10));
        assertThat(account.getCheckoutDate()).isEqualTo(LocalDate.of(2016, 1, 10));
    }

    @Test
    public void testToString() {
        Account account = new Account
                ("Petar Kirov", "Joy of C++", LocalDate.of(2016, 1, 10));
        assertThat(account.toString()).
                isEqualTo("Account{Petar Kirov, Joy of C++, 2016-01-10}");
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testThatNameIsNotNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Name cannot be null");
        new Account
                (null, "Joy of C++", LocalDate.of(2016, 1, 10));
    }

    @Test
    public void testThatTitleIsNotNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Title cannot be null");
        new Account
                ("Petar Kirov", null, LocalDate.of(2016, 1, 10));
    }

    @Test
    public void testThatCheckoutDateIsNotNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Checkout date cannot be null");
        new Account
                ("Petar Kirov", "Joy of C++", null);
    }
}
