package br.edu.ufabc.lexicon.business;

import org.junit.Assert;
import org.junit.Test;

public class VoteAndFlipTest {
    @Test
    public void testSentence(){
        String sentence = "I like the course but don't like the teacher";
        VoteAndFlip flip =  new VoteAndFlip(sentence, 2);

        Assert.assertEquals(2,flip.finalPolarity());
    }
}
