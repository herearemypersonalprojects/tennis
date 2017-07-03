package com.quocanhle.tennis.tdd;

import com.quocanhle.tennis.IllegalMatchException;
import com.quocanhle.tennis.Match;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by quocanh on 03/07/2017.
 */
public class MatchTest extends TestCase {

    @Test
    public void testPlayerOneScores() throws Exception {
        Match match = new Match("David", "Huy");
        match.playerOneScores();
        Assert.assertTrue(match.getCurrentStatus().equals("15-0"));
    }

    @Test
    public void testPlayerOneScoresIllegal() throws IllegalMatchException {
        Match match = new Match("David", "Huy");
        try {
            for (int i = 0; i < 24 * 4 + 1; i++) {
                match.playerOneScores();
            }
        } catch (IllegalMatchException ex) {
            Assert.assertTrue(ex.toString().equals("The match has already finished!!!"));
        }

    }

    public void testPlayerTwoScores() throws Exception {

    }

    public void testGetResult() throws Exception {

    }

    public void testGetCurrentScore() throws Exception {

    }

    public void testGetCurrentMatchStatus() throws Exception {

    }

    public void testGetCurrentStatus() throws Exception {

    }
}