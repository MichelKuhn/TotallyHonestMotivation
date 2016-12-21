package com.example.michel.totallyhonestmotivation;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void quoteGenerator_works() throws Exception {
        QuoteGenerator testGenerator = new QuoteGenerator();
        assertEquals("Du bist schick, denn du bist ein Huhn.", testGenerator.generateQuote());
    }
}