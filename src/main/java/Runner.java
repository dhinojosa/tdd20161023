import com.xyzcorp.Account;
import com.xyzcorp.AccountParser;
import com.xyzcorp.PenaltyCalculator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.stream.Stream;

/**
 * Created by danno on 10/26/16.
 */
public class Runner {

    public static void main(String[] args) throws Exception {
        try (InputStream inputStream = Runner.class.getResourceAsStream("/library.txt");
             InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            Stream<String> sorted = AccountParser.parseStream(bufferedReader.lines())
                    .sorted((a1, a2) ->
                          a1.getCheckoutDate().compareTo(a2.getCheckoutDate())).limit(5)
                            .map(a -> a.getName() + " " + PenaltyCalculator
                                    .calculate(a.getCheckoutDate(), LocalDate.now(), 10));
            sorted.forEach(System.out::println);
        }
    }
}
