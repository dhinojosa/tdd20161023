package com.xyzcorp;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzzTest {

    @Test
    public void testFor1() {
        assertThat(FizzBuzz.convert(1)).isEqualTo("1");
    }

    @Test
    public void testFor2() {
        assertThat(FizzBuzz.convert(2)).isEqualTo("2");
    }

    @Test
    public void testFor3() {
        assertThat(FizzBuzz.convert(3)).isEqualTo("Fizz");
    }

    @Test
    public void testFor5() {
        assertThat(FizzBuzz.convert(5)).isEqualTo("Buzz");
    }

    @Test
    public void testFor10() {
        assertThat(FizzBuzz.convert(10)).isEqualTo("Buzz");
    }

    @Test
    public void testFor6() {
        assertThat(FizzBuzz.convert(6)).isEqualTo("Fizz");
    }

    @Test
    public void testFor7() {
        assertThat(FizzBuzz.convert(7)).isEqualTo("7");
    }

    @Test
    public void testFor15() {
        assertThat(FizzBuzz.convert(15)).isEqualTo("FizzBuzz");
    }

    @Test
    public void testFor30() {
        assertThat(FizzBuzz.convert(30)).isEqualTo("FizzBuzz");
    }

    @Test
    public void testStreamFactory() {
        Stream<String> integerStream = FizzBuzz.streamFromOneTo(5);
        List<String> result = integerStream.collect(Collectors.toList());
        assertThat(result).hasSize(5);
        assertThat(result.get(0)).isEqualTo("1");
        assertThat(result.get(4)).isEqualTo("Buzz");
    }
}
