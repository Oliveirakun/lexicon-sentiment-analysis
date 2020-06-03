package br.edu.ufabc.lexicon.business;

public class FeedbackAnalysis {
    private String originalFeedback;
    private SentimentFeedback sentimentFeedback;

    public FeedbackAnalysis(String originalFeedback, SentimentFeedback sentimentFeedback) {
        this.originalFeedback = originalFeedback;
        this.sentimentFeedback = sentimentFeedback;
    }

    public String getOriginalFeedback() {
        return originalFeedback;
    }

    public int getSentimentScore() {
        return sentimentFeedback.sentimentScore();
    }

    public int flipedPolarity() {
        VoteAndFlip flip = new VoteAndFlip(originalFeedback, sentimentFeedback.sentimentScore());
        return flip.finalPolarity();
    }
}
