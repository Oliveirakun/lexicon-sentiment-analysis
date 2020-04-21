package br.edu.ufabc.lexicon.business;

import br.edu.ufabc.lexicon.processors.FeedbackProcessor;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SentimentAnalyser {
    private List<String> feedbacks;
    private SentimentDictionary sentimentDictionary;

    public SentimentAnalyser(List<String> feedbacks) {
        this.feedbacks = feedbacks;
        sentimentDictionary = new SentimentDictionary();
    }

    public List<FeedbackAnalysis> getAnalyzedFeedbacks() {
       return feedbacks.stream()
            .map(feedback-> {
                List<String> words = new FeedbackProcessor(feedback).process();
                SentimentFeedback sentimentFeedback = new SentimentFeedback();
                words.forEach(word-> {
                    SentimentWord.Polarity polarity = sentimentDictionary.findPolarity(word);
                    if (polarity != null) {
                        sentimentFeedback.addWord(word, polarity);
                    }
                });

                return new FeedbackAnalysis(feedback, sentimentFeedback);
            })
            .collect(Collectors.toList());
    }

    public List<SentimentWord> getWordsOverallAttitude() {
        SentimentFeedback sentimentFeedback = new SentimentFeedback();

        feedbacks.stream()
            .map(feedback-> {
                return new FeedbackProcessor(feedback).process();
            })
            .flatMap(Collection::stream)
            .forEach(word-> {
                SentimentWord.Polarity polarity = sentimentDictionary.findPolarity(word);
                if (polarity != null) {
                    sentimentFeedback.addWord(word, polarity);
                }
            });

        return sentimentFeedback.getWordsList();
    }
}
