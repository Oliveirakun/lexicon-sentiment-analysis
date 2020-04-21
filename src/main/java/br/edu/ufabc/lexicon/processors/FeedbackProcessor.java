package br.edu.ufabc.lexicon.processors;

import edu.stanford.nlp.simple.Sentence;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FeedbackProcessor {
    private String feedback;

    public FeedbackProcessor(String feedback) {
        this.feedback = feedback;
    }

    public List<String> process() {
        return Stream
                .of(feedback.split(" "))
                .map(word-> word.toLowerCase().trim())
                .filter(this::isNotStopWord)
                .map(word-> word.replaceAll("[^a-zA-Z ]", ""))
                .filter(word-> !word.isEmpty())
                .map(word-> new Sentence(word).lemma(0))
                .filter(this::isNotStopWord)
                .collect(Collectors.toList());
    }

    private boolean isNotStopWord(String word) {
        return !stopWordsList().contains(word);
    }

    private List<String> stopWordsList() {
        return Arrays.asList(
            "and",
            "also",
            "it",
            "to",
            "the",
            "relate",
            "for",
            "from",
            "in",
            "of",
            "on",
            "with",
            "at",
            "since",
            "ago",
            "before",
            "past",
            "till",
            "until",
            "by",
            "next",
            "beside",
            "over",
            "bellow",
            "across",
            "through",
            "about",
            "onto",
            "we"
        );
    }
}
