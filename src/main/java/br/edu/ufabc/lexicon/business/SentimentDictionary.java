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
        return Collections.binarySearch(positiveList, word) >= 0;
    }

    public boolean isNegative(String word) {
        return Collections.binarySearch(negativeList, word) >= 0;
    }

    private void setNegativeList() throws URISyntaxException, IOException {
        Path path = Paths.get(getClass().getClassLoader()
            .getResource("negative.txt").toURI());

        Stream<String> lines = Files.lines(path);
        negativeList = lines.map(word-> word.replaceAll("\"",""))
                                 .collect(Collectors.toList());

        modifyPolarityOfNegativeWords();
        Collections.sort(negativeList);
        lines.close();
    }

    private void setPositiveList() throws URISyntaxException, IOException {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource("positive.txt").toURI());

        Stream<String> lines = Files.lines(path);
        positiveList = lines.map(word-> word.replaceAll("\"",""))
                                 .collect(Collectors.toList());

        modifyPolarityOfPositiveWords();
        Collections.sort(positiveList);
        lines.close();
    }

    private void modifyPolarityOfPositiveWords() {
        positiveList.add("challenge");
        positiveList.add("overcome");
        positiveList.add("concern");
        positiveList.add("concerned");
        positiveList.add("concerns");
    }

    private void modifyPolarityOfNegativeWords() {
        negativeList.removeAll(Arrays.asList("fun"));
        negativeList.removeAll(Arrays.asList("fine"));
        negativeList.removeAll(Arrays.asList("miss"));
        negativeList.removeAll(Arrays.asList("challenge"));
        negativeList.removeAll(Arrays.asList("extreme"));
        negativeList.removeAll(Arrays.asList("extremely"));
        negativeList.removeAll(Arrays.asList("thumb"));
        negativeList.removeAll(Arrays.asList("object"));
        negativeList.removeAll(Arrays.asList("overcome"));
        negativeList.removeAll(Arrays.asList("lecture"));
        negativeList.removeAll(Arrays.asList("negative"));
        negativeList.removeAll(Arrays.asList("concern"));
        negativeList.removeAll(Arrays.asList("concerned"));
        negativeList.removeAll(Arrays.asList("concerns"));
        negativeList.removeAll(Arrays.asList("extreme"));
        negativeList.removeAll(Arrays.asList("need"));
    }
}
