package br.edu.ufabc.lexicon;

import br.edu.ufabc.lexicon.business.SentimentAnalyser;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.PolarBlendMode;
import com.kennycason.kumo.PolarWordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Path path = Paths.get(Main.class.getClassLoader().getResource("feedbacks.txt").toURI());
        List<String> feedbacks = Files.lines(path).collect(Collectors.toList());

        SentimentAnalyser analyser =  new SentimentAnalyser(feedbacks);

        System.out.println("Feedback: " + analyser.getAnalyzedFeedbacks().get(10).getOriginalFeedback());
        System.out.println("Sentiment Score: " + analyser.getAnalyzedFeedbacks().get(10).getSentimentScore());

        analyser.getWordsOverallAttitude().forEach(word-> {
            System.out.println("Word: " + word.getWord() + " Attitude: " + word.overallAttitude());
        });

        buildWordCloud(analyser);
    }

    public static void buildWordCloud(SentimentAnalyser analyser) throws IOException {
        List<WordFrequency> positiveWords = analyser.getWordsOverallAttitude()
                .stream()
                .filter(word-> word.overallAttitude() > 0)
                .map(word-> new WordFrequency(word.getWord(), word.overallAttitude()))
                .collect(Collectors.toList());

        List<WordFrequency> negativeWords = analyser.getWordsOverallAttitude()
                .stream()
                .filter(word-> word.overallAttitude() < 0)
                .map(word-> new WordFrequency(word.getWord(), Math.abs(word.overallAttitude())))
                .collect(Collectors.toList());

        Dimension dimension = new Dimension(600, 480);
        PolarWordCloud wordCloud = new PolarWordCloud(dimension, CollisionMode.PIXEL_PERFECT, PolarBlendMode.BLUR);
        wordCloud.setPadding(2);
        wordCloud.setBackground(new CircleBackground(200));
//        wordCloud.setBackgroundColor(Color.WHITE);
        wordCloud.setFontScalar(new SqrtFontScalar(10, 60));
        wordCloud.build(positiveWords, negativeWords);
        File tempFile = File.createTempFile("words",".png");
        wordCloud.writeToFile(tempFile.getPath());

        showImage(tempFile);
        tempFile.delete();
    }

    public static void showImage(File file) {
        JFrame frame = new JFrame();
        JLabel lblimage = new JLabel(new ImageIcon(file.getPath()));
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(lblimage);
        frame.add(mainPanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
