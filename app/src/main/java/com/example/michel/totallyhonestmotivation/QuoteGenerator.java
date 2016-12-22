package com.example.michel.totallyhonestmotivation;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by michel on 21.12.16.
 */

public class QuoteGenerator {
    private static String ADJECTIVE_PATTERN = "\\[Adjektiv\\]";
    private static String SUBSTANTIV_PATTERN = "\\[Substantiv\\]";

    private static ArrayList<String> templates = new ArrayList<String>();
    private static ArrayList<String> adjektive = new ArrayList<String>();
    private static ArrayList<String> substantive = new ArrayList<String>();

    public QuoteGenerator() {
        templates.add("Dein [Substantiv] ist [Adjektiv], denn du bist ein Mensch.");
        templates.add("Es ist dein [Substantiv].");
        adjektive.add("wundervoll");
        adjektive.add("traumhaft");
        adjektive.add("besser");
        adjektive.add("besonders");
        substantive.add("Leben");
        substantive.add("Nebel");
        substantive.add("Weg");
        substantive.add("Traum");
    }

    private static String createTemplate() {
        return templates.get(new Random().nextInt(templates.size()));
    }

    private static String createRandomWord(String type) {
        switch (type) {
            case "ADJEKTIV": return adjektive.get(new Random().nextInt(adjektive.size()));
            case "SUBSTANTIV": return substantive.get(new Random().nextInt(substantive.size()));
            default: return null;
        }
    }

    private static String replaceWords(String quote) {
        String temp = quote.replaceAll(ADJECTIVE_PATTERN, createRandomWord("ADJEKTIV"));
        return temp.replaceAll(SUBSTANTIV_PATTERN, createRandomWord("SUBSTANTIV"));
    }

    public String generateQuote() {
        return replaceWords(createTemplate());
    }
}
