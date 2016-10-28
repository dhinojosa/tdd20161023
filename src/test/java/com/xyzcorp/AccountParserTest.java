package com.xyzcorp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by danno on 10/26/16.
 */
public class AccountParserTest {
    @Test
    public void testPerfectScenario() throws Exception {
        Account account = AccountParser.parse("Kostadin Terziev~Airport~2015-03-10");
        assertThat(account.getName()).isEqualTo("Kostadin Terziev");
        assertThat(account.getTitle()).isEqualTo("Airport");
        assertThat(account.getCheckoutDate()).isEqualTo(LocalDate.of(2015, 3, 10));
    }

    @Test
    public void testPerfectScenarioDifferentData() throws Exception {
        Account account = AccountParser.parse("Ivo Rahov~The Evolution Lie~2016-02-15");
        assertThat(account.getName()).isEqualTo("Ivo Rahov");
        assertThat(account.getTitle()).isEqualTo("The Evolution Lie");
        assertThat(account.getCheckoutDate()).isEqualTo(LocalDate.of(2016, 2, 15));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testIfDataIsBlankLine() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Line cannot be blank");
        AccountParser.parse("");
    }

    @Test
    public void testIfDataIsBlankLinesWithSpaces() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Line cannot be blank");
        AccountParser.parse("     ");
    }

    @Test
    public void testWithMoreThan3Items() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Line is invalid format. Expected: name~title~iso8601localDate. Actual: Ivo Rahov~The Evolution Lie~2016-02-15~Hello");
        AccountParser.parse("Ivo Rahov~The Evolution Lie~2016-02-15~Hello");
    }

    @Test
    public void testNotNullLine() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Line cannot be null, please provide a line");
        AccountParser.parse(null);
    }

    @Test
    public void testWithLessThan3Items() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Line is invalid format. Expected: name~title~iso8601localDate. Actual: The Evolution Lie~2016-02-15");
        AccountParser.parse("The Evolution Lie~2016-02-15");
    }

    @Test
    public void testThatTheDateIsInTheWrongFormat() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Line is invalid format. Expected: name~title~iso8601localDate. Actual: Kostadin Terziev~Airport~2015-14-10");
        AccountParser.parse("Kostadin Terziev~Airport~2015-14-10");
    }

    @Test
    public void testWithNoName() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Line is invalid format. Expected: name~title~iso8601localDate. Actual: ~The Evolution Lie~2016-02-15");
        AccountParser.parse("~The Evolution Lie~2016-02-15");
    }

    @Test
    public void testWithNoTitle() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Line is invalid format. Expected: name~title~iso8601localDate. Actual: Ivo Rahov~~2016-02-15");
        AccountParser.parse("Ivo Rahov~~2016-02-15");
    }

    @Test
    public void testWithNoDate() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Line is invalid format. Expected: name~title~iso8601localDate. Actual: Ivo Rahov~The Evolution Lie~");
        AccountParser.parse("Ivo Rahov~The Evolution Lie~");
    }

    //Find the highest abstract
    @Test
    public void testParseStreamPerfectCondition() {
        Stream<String> stringStream = Stream.of("Kostadin Terziev~Airport~2015-14-10");
        Stream<Account> accountStream = AccountParser.parseStream(stringStream);
        assertThat(accountStream.findFirst().get().getName())
                .isEqualTo("Kostadin Terziev");
    }

    @Test
    public void testParseStreamPerfectConditionAlternate() {
        Stream<String> stringStream = Stream.of("Ivo Rahov~The Evolution Lie~2016-02-15");
        Stream<Account> accountStream = AccountParser.parseStream(stringStream);
        assertThat(accountStream.findFirst().get().getName())
                .isEqualTo("Ivo Rahov");
    }

    @Test
    public void testParseStreamPerfectConditionTwoItems() {
        Stream<String> stringStream = Stream.of(
                "Ivo Rahov~The Evolution Lie~2016-02-15",
                "Kostadin Terziev~Airport~2015-03-10");
        Stream<Account> accountStream = AccountParser.parseStream(stringStream);
        assertThat(accountStream.count()).isEqualTo(2);
    }

    @Test
    public void testParseFromMultilineString() {
        String data = "Venkat Subramaniam~Function Programming~2015-03-10\n" +
                "Brian Sletten~A Supposedly Fun Thing I Will Never Do Again~2015-02-12\n" +
                "Beth Brown~The Leftovers~2013-03-31\n" +
                "Janelle Klein~On Intelligence~2014-07-21\n" +
                "Jim Price~The Girl In The Spider's Web~2015-08-15";

        String[] lines = data.split("\n");
        Stream<Account> accountStream = AccountParser.parseStream(Stream.of(lines));
        assertThat(accountStream.count()).isEqualTo(5);
    }

    @Test
    public void testParseFromMultilineStringUsingBufferedReader() {
        String data = "Venkat Subramaniam~Function Programming~2015-03-10\n" +
                "Brian Sletten~A Supposedly Fun Thing I Will Never Do Again~2015-02-12\n" +
                "Beth Brown~The Leftovers~2013-03-31\n" +
                "Janelle Klein~On Intelligence~2014-07-21\n" +
                "Jim Price~The Girl In The Spider's Web~2015-08-15";

        StringReader stringReader = new StringReader(data);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        Stream<Account> accountStream = AccountParser.parseStream(bufferedReader.lines());
        assertThat(accountStream.count()).isEqualTo(5);
    }

    @Test
    public void testParseFromMultilineStringUsingBufferedReaderWithBadDate() {
        String data = "Venkat Subramaniam~Function Programming~2015-03-10\n" +
                "Brian Sletten~A Supposedly Fun Thing I Will Never Do Again~2015-02-12\n" +
                "Beth Brown~The Leftovers~2013-03-31\n" +
                "Janelle Klein~On Intelligence~2014-19-21\n" +
                "Jim Price~The Girl In The Spider's Web~2015-08-15";

        StringReader stringReader = new StringReader(data);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Line is invalid format. " + "" +
                "Expected: name~title~iso8601localDate. " +
                "Actual: Janelle Klein~On Intelligence~2014-19-21");
        AccountParser.parseStream(bufferedReader.lines()).count();
    }

    @Test
    @Category(value = IntegrationTest.class)
    public void testReadFromTheClasspath() throws Exception {
        try (InputStream inputStream = getClass().getResourceAsStream("/library.txt");
             InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            assertThat(AccountParser.parseStream(bufferedReader.lines())).hasSize(18);
        }
    }

    @Test
    @Category(value = IntegrationTest.class)
    public void testReadFromTheURL() throws Exception {
        URL url = new URL("https://gist.githubusercontent.com/dhinojosa/59520dfabc6282ce4e732e5f41eaf476/raw/6e335ae5df4161da3d4d73057cd84b387154accd/library.txt");
        try (InputStream inputStream = url.openStream();
             InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            assertThat(AccountParser.parseStream(bufferedReader.lines())).hasSize(16);
        }
    }


}


