package com.example.michel.totallyhonestmotivation;
import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by michel on 21.12.16.
 */

class QuoteGenerator {
    private static List<String> templates = new ArrayList<>();
    private static List<String> adjektive = new ArrayList<>();
    private static List<String> substantivesS = new ArrayList<>();
    private static List<String> substantivesP = new ArrayList<>();
    private static List<String> verbs = new ArrayList<>();

    QuoteGenerator(Context context) {
        templates = Arrays.asList(context.getResources().getStringArray(R.array.templates));
        adjektive = Arrays.asList(context.getResources().getStringArray(R.array.adjectives));
        substantivesS = Arrays.asList(context.getResources().getStringArray(R.array.substantivesS));
        substantivesP = Arrays.asList(context.getResources().getStringArray(R.array.substantivesP));
        verbs = Arrays.asList(context.getResources().getStringArray(R.array.verbs));
    }

    private static String createTemplate() {
        return templates.get(new Random().nextInt(templates.size()));
    }

    private static String createRandomWord(String type) {
        switch (type) {
            case "ADJEKTIV": return adjektive.get(new Random().nextInt(adjektive.size()));
            case "SUBSTANTIV_S": return substantivesS.get(new Random().nextInt(substantivesS.size()));
            case "SUBSTANTIV_P": return substantivesP.get(new Random().nextInt(substantivesP.size()));
            case "VERB": return verbs.get(new Random().nextInt(verbs.size()));
            default: return "BOB";
        }
    }

    private static String replaceWords(String quote) {
        String temp = quote.replaceAll("\\[Adjektiv\\]", createRandomWord("ADJEKTIV"));
        String temp2 = temp.replaceAll("\\[SubstantivP\\]", createRandomWord("SUBSTANTIV_P"));
        temp = temp2.replaceAll("\\[Verb\\]", createRandomWord("VERB"));
        return temp.replaceAll("\\[SubstantivS\\]", createRandomWord("SUBSTANTIV_S"));
    }

    String generateQuote() {
        return replaceWords(createTemplate());
    }
}
