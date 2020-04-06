package br.edu.ufabc.lexicon.business;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class SentimentDictionaryTest {
    @Test
    public void testData() throws UnsupportedEncodingException {
        SentimentDictionary dictionary = new SentimentDictionary();

        Assert.assertEquals(true, dictionary.isPositive("stable"));
        Assert.assertEquals(true, dictionary.isNegative("bad"));

        Assert.assertEquals(false, dictionary.isNegative("stable"));
        Assert.assertEquals(false, dictionary.isPositive("bad"));
    }
}
