package br.edu.ufabc.lexicon.business;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SentimentDictionary {
    private List<String> positiveList;
    private List<String> negativeList;

    public SentimentDictionary() {
        try {
            setNegativeList();
            setPositiveList();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SentimentWord.Polarity findPolarity(String word) {
        if (isPositive(word)) {
            return SentimentWord.Polarity.POSITIVE;
        } else if (isNegative(word)) {
            return SentimentWord.Polarity.NEGATIVE;
        } else {
            return null;
        }
    }

    public boolean isPositive(String word) {
        return positiveList.contains(word);
    }

    public boolean isNegative(String word) {
        return negativeList.contains(word);
    }

    private void setNegativeList() throws URISyntaxException, IOException {
        Path path = Paths.get(getClass().getClassLoader()
            .getResource("negative.txt").toURI());

        Stream<String> lines = Files.lines(path);
        this.negativeList = lines.map(word-> word.replaceAll("\"",""))
                                 .collect(Collectors.toList());
        lines.close();
    }

    private void setPositiveList() throws URISyntaxException, IOException {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource("positive.txt").toURI());

        Stream<String> lines = Files.lines(path);
        this.positiveList = lines.map(word-> word.replaceAll("\"",""))
                                 .collect(Collectors.toList());
        lines.close();
    }
}
