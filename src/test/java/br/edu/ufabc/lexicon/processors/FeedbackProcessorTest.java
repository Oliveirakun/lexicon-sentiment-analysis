package br.edu.ufabc.lexicon.processors;

import edu.stanford.nlp.simple.Sentence;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class FeedbackProcessorTest {
    @Test
    public void testOutput() {
        StringBuilder feedback = new StringBuilder()
            .append("Difficult course, great teacher and also ")
            .append("able to relate it to the practical knowledge.");

        List<String> expectedResult = Arrays.asList(
            "difficult",
            "course",
            "great",
            "teacher",
            "able",
            "practical",
            "knowledge"
        );

        FeedbackProcessor processor = new FeedbackProcessor(feedback.toString());
        List<String> result = processor.process();

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testOutput2() {
        String feedback = "The waiter ignored us for 20 minutes";

        List<String> expectedResult = Arrays.asList(
            "waiter",
            "ignore",
            "minute"
        );

        FeedbackProcessor processor = new FeedbackProcessor(feedback.toString());
        List<String> result = processor.process();

        Assert.assertEquals(expectedResult, result);
    }
}
