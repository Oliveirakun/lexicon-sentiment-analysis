package br.edu.ufabc.lexicon.business;

import br.edu.ufabc.lexicon.processors.FeedbackProcessor;

import java.util.Arrays;
import java.util.List;

public class VoteAndFlip {
    private String feedback;
    private int priorPolarity;

    public VoteAndFlip(String feedback, int priorPolarity) {
        this.feedback = feedback;
        this.priorPolarity = priorPolarity;
    }

    public int finalPolarity() {
        List<String> words = new FeedbackProcessor(feedback).process();
        int finalPolarity = priorPolarity;
        boolean flipPolarity = false;
        long sumNegatingWords = words
            .stream()
            .filter(word-> negatingWords().contains(word))
            .count();

        if (!(sumNegatingWords % 2 == 0))
            flipPolarity = true;

        if (priorPolarity > 0 && flipPolarity)
            finalPolarity = priorPolarity - 2;
        else if (priorPolarity < 0 && flipPolarity)
            finalPolarity = priorPolarity + 2;
        else if (priorPolarity == 0 && flipPolarity)
            finalPolarity = -1;

        return finalPolarity;
    }

    private List<String> negatingWords() {
        return Arrays.asList(
            "aint", "doesnt", "havent", "lacks", "none", "mightnt", "shouldnt", "cannot",
            "dont", "havnt", "neither", "nor", "mustnt", "wasnt", "cant", "hadnt", "isnt",
            "never", "not", "neednt", "without", "darent", "hardly", "lack", "no", "nothing",
            "oughtnt", "wouldnt", "didnt", "hasnt", "lacking", "nowhere", "shant", "but"
        );
    }
}
