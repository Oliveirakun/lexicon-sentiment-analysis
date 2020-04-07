package br.edu.ufabc.lexicon.business;

import org.junit.Assert;
import org.junit.Test;

public class SentimentFeedbackTest {
    @Test
    public void testSentimentScore() {
        SentimentFeedback feedback = new SentimentFeedback();
        feedback.addWord("nice", SentimentWord.Polarity.POSITIVE);
        feedback.addWord("good", SentimentWord.Polarity.POSITIVE);
        feedback.addWord("boring", SentimentWord.Polarity.NEGATIVE);

        Assert.assertEquals(1, feedback.sentimentScore());
    }

    @Test
    public void testSentimentScoreWithRepeatedWords() {
        SentimentFeedback feedback = new SentimentFeedback();
        feedback.addWord("nice", SentimentWord.Polarity.POSITIVE);
        feedback.addWord("good", SentimentWord.Polarity.POSITIVE);

        // He should ignore the polarity of a repeated element
        feedback.addWord("good", SentimentWord.Polarity.NEGATIVE);
        feedback.addWord("boring", SentimentWord.Polarity.NEGATIVE);

        Assert.assertEquals(2, feedback.sentimentScore());
    }
}
