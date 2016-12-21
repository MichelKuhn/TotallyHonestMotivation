package com.example.michel.totallyhonestmotivation;

/**
 * Created by michel on 21.12.16.
 */

public class QuoteGenerator {
    private static String ADJECTIVE_PATTERN = "\\[Adjektiv\\]";
    private static String SUBSTANTIV_PATTERN = "\\[Substantiv\\]";

    private static String template = "Du bist [Adjektiv], denn du bist ein [Substantiv].";

    private static String replaceWords(String quote) {
        String temp = quote.replaceAll(ADJECTIVE_PATTERN, "schick");
        return temp.replaceAll(SUBSTANTIV_PATTERN, "Huhn");
    }

    public String generateQuote() {
        return replaceWords(template);
    }
}
