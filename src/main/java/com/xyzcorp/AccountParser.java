package com.xyzcorp;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Created by danno on 10/26/16.
 */
public class AccountParser {

    private static IllegalArgumentException createInvalidFormatException(String msg) {
        return new IllegalArgumentException(
                "Line is invalid format. Expected: name~title~iso8601localDate. Actual: " + msg);
    }
    public static Account parse(String data) {
        Objects.requireNonNull(data, "Line cannot be null, please provide a line");
        if (data.trim().isEmpty())
            throw new IllegalArgumentException("Line cannot be blank");
        String[] array = data.split("~");
        if (array.length != 3 || array[0].trim().isEmpty() || array[1].trim().isEmpty())
            throw createInvalidFormatException(data);
        try {
            return new Account(array[0], array[1], LocalDate.parse(array[2]));
        } catch (DateTimeParseException dpe) {
            throw createInvalidFormatException(data);
        }
    }

    public static Stream<Account> parseStream(Stream<String> stringStream) {
        return stringStream.map(AccountParser::parse);
    }
}
