package com.xyzcorp;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by danno on 10/25/16.
 */
public class FizzBuzz {
    public static String convert(int i) {
        if (i % 5 == 0 && i % 3 == 0) return "FizzBuzz";
        else if (i % 3 == 0) return "Fizz";
        else if (i % 5 == 0) return "Buzz";
        else return "" + i;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println(convert(i));
        }

        IntStream.rangeClosed(1, 100).boxed()
                .map(FizzBuzz::convert).forEach(System.out::println);

        FizzBuzz.streamFromOneTo(100).forEach(System.out::println);
    }

    public static Stream<String> streamFromOneTo(int i) {
        return IntStream.rangeClosed(1, i).boxed().map(x -> convert(x));
    }
}
