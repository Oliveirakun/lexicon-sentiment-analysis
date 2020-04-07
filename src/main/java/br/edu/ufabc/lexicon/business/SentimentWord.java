package br.edu.ufabc.lexicon.business;

public class SentimentWord {
    private String word;
    private int frequency;
    private Polarity polarity;
    public enum Polarity {
        POSITIVE, NEGATIVE
    }

    public SentimentWord(String word, Polarity polarity) {
        this.word = word;
        this.polarity = polarity;
        this.frequency = 1;
    }

    public String getWord() {
        return word;
    }

    public void incrementFrequency() {
        frequency++;
    }

    public int overallAttitude() {
        int attitude = polarity.equals(Polarity.POSITIVE) ? 1 : -1;

        return attitude * frequency;
    }
}
