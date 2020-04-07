package br.edu.ufabc.lexicon.business;

import java.util.List;

public class SentimentAnalyser {
    private List<String> words;
    private SentimentDictionary sentimentDictionary;

    public SentimentAnalyser(List<String> words) {
        this.words = words;
        sentimentDictionary = new SentimentDictionary();
    }

    public void polarityTagging() {

    }

}
