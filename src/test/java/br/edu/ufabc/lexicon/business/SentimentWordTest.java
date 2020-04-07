package br.edu.ufabc.lexicon.business;

import org.junit.Assert;
import org.junit.Test;

public class SentimentWordTest {
    @Test
    public void testPositiveSentimentWord() {
        String word = "good";
        SentimentWord sentimentWord = new SentimentWord(word, SentimentWord.Polarity.POSITIVE);
        sentimentWord.incrementFrequency();

        Assert.assertEquals("good", sentimentWord.getWord());
        Assert.assertEquals(2, sentimentWord.overallAttitude());
    }

    @Test
    public void testNegativeSentimentWord() {
        String word = "boring";
        SentimentWord sentimentWord = new SentimentWord(word, SentimentWord.Polarity.NEGATIVE);
        sentimentWord.incrementFrequency();

        Assert.assertEquals("boring", sentimentWord.getWord());
        Assert.assertEquals(-2, sentimentWord.overallAttitude());
    }
}
