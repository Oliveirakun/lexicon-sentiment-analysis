package br.edu.ufabc.lexicon;

import br.edu.ufabc.lexicon.business.FeedbackAnalysis;
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
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Path pathFeedbacks = Paths.get(Main.class.getClassLoader().getResource("feedbacks.txt").toURI());
        Path pathPolarities = Paths.get(Main.class.getClassLoader().getResource("feedbacks-polarity.txt").toURI());

        List<String> feedbacks = Files.lines(pathFeedbacks).collect(Collectors.toList());
        List<String> polarities = Files.lines(pathPolarities).collect(Collectors.toList());

        SentimentAnalyser analyser =  new SentimentAnalyser(feedbacks);

        int totalCorrect = 0;
        int totalCorrectOriginalPolarity = 0;
        for(int i=0;i<feedbacks.size();i++) {
            FeedbackAnalysis feedback = analyser.getAnalyzedFeedbacks().get(i);
            System.out.println("Feedback: " + feedback.getOriginalFeedback());
            System.out.println("Sentiment Score: " + feedback.getSentimentScore());
            System.out.println("Fliped polarity: " + feedback.flipedPolarity());
            System.out.println("Desired: " + polarities.get(i));

            // Fliped
            if (feedback.flipedPolarity() > 0 && polarities.get(i).equals("positive"))
                totalCorrect++;

            if (feedback.flipedPolarity() < 0 && polarities.get(i).equals("negative"))
                totalCorrect++;

            if (feedback.flipedPolarity() == 0 && polarities.get(i).equals("neutral"))
                totalCorrect++;

            // Original
            if (feedback.getSentimentScore() > 0 && polarities.get(i).equals("positive"))
                totalCorrectOriginalPolarity++;

            if (feedback.getSentimentScore() > 0 && polarities.get(i).equals("negative"))
                totalCorrectOriginalPolarity++;

            if (feedback.getSentimentScore() == 0 && polarities.get(i).equals("neutral"))
                totalCorrectOriginalPolarity++;

        }

        double totalOriginal = ((double)totalCorrectOriginalPolarity / (double)feedbacks.size()) * 100;
        System.out.println("Percentage correct original: " + totalOriginal);

        double total = ((double)totalCorrect / (double)feedbacks.size()) * 100;
        System.out.println("Porcentage correct flipped: " + total);

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
        wordCloud.setBackground(new CircleBackground(300));
        wordCloud.setFontScalar(new SqrtFontScalar(10, 50));
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


