package br.edu.ufabc.lexicon.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SentimentFeedback {
    private List<SentimentWord> wordsList;

    public  SentimentFeedback() {
        this.wordsList = new ArrayList<>();
    }

    public void addWord(String word, SentimentWord.Polarity polarity) {
        SentimentWord sentimentWord = findWord(word);
        if (sentimentWord != null) {
            sentimentWord.incrementFrequency();
            return;
        }

        sentimentWord = new SentimentWord(word, polarity);
        wordsList.add(sentimentWord);
    }

    public int sentimentScore() {
        return wordsList.stream()
                        .map(word-> word.overallAttitude())
                        .reduce(0, Integer::sum);
    }

    private SentimentWord findWord(String word) {
        Optional<SentimentWord> sentWord = wordsList
                        .stream()
                        .filter(sword-> sword.getWord().equals(word))
                        .map(Optional::ofNullable)
                        .findFirst()
                        .orElse(null);

        return sentWord == null ? null : sentWord.get();
    }
}
