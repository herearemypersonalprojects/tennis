package com.quocanhle.tennis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by quocanh on 01/07/2017.
 */
public class Match {
    private String name1;
    private String name2;

    private int pointNb1;
    private int pointNb2;

    class TennisSet {
        int gameNb1;
        int gameNb2;

        public TennisSet(int gameNb1, int gameNb2) {
            this.gameNb1 = gameNb1;
            this.gameNb2 = gameNb2;
        }
    }
    private List<TennisSet> tennisSets = new ArrayList<>();
    private TennisSet currentSet;

    private int setNb1;
    private int setNb2;

    public Match(String name1, String name2) {
        this.name1 = name1;
        this.name2 = name2;

        currentSet = new TennisSet(0, 0);
        tennisSets.add(currentSet);
    }

    private void recaculGame() {
        if (hasGameWinner()) {
            if (playerWithHighestPoints().equals(name1)) {
                currentSet.gameNb1++;
            } else {
                currentSet.gameNb2++;
            }

            if (hasSetWinner()) {

                if (playerWithHighestPoints().equals(name1)) {
                    setNb1++;
                } else {
                    setNb2++;
                }

                if (!hasMatchWinner()) {
                    currentSet = new TennisSet(0, 0);
                    tennisSets.add(currentSet);
                }
            }

            pointNb1 = 0;
            pointNb2 = 0;
        }
    }

    public void playerOneScores() throws IllegalMatchException {
        if (!hasMatchWinner()) {
            pointNb1++;
            recaculGame();
        } else {
            throw new IllegalMatchException();
        }
    }

    public void playerTwoScores() throws IllegalMatchException {
        if (!hasMatchWinner()) {
            pointNb2++;
            recaculGame();
        } else {
            throw new IllegalMatchException();
        }
    }

    /**
     * To win the match, a player must win 3 sets.
     */
    private boolean hasMatchWinner() {
        if(setNb1 == 3 || setNb2 == 3)
            return true;
        return false;
    }


    /**
     * To win a game, a player has 40 and scores
     */
    private boolean hasGameWinner() {
        if(pointNb1 >= 4 && pointNb1 >= pointNb2 + 2 )
            return true;
        if(pointNb2 >= 4 && pointNb2 >= pointNb1 + 2)
            return true;
        return false;
    }

    /**
     * To win a set, a player must win : at least 6 games and 2 more games than the opponent
     * If both players have 6 games, then the next game is a tie-break. The winner of the tie-breaker game wins the set.
     * To win a tie-break game, a player must score : at least 7 points and 2 more points than the opponent
     * @return boolean
     */
    private boolean hasSetWinner() {
        if(currentSet.gameNb1 >= 6 && currentSet.gameNb1 >= currentSet.gameNb2 + 2 )
            return true;
        if(currentSet.gameNb2 >= 6 && currentSet.gameNb2 >= currentSet.gameNb1 + 2)
            return true;
        return false;
    }

    public String getResult() {
        StringBuilder result = new StringBuilder();

        result.append("Player 1 : " + name1);
        result.append(System.getProperty("line.separator"));

        result.append("Player 2 : " + name2);
        result.append(System.getProperty("line.separator"));

        result.append("Score : " + getCurrentScore());
        result.append(System.getProperty("line.separator"));

        if (!hasMatchWinner()) {
            result.append("Current game status : " + getCurrentStatus());
            result.append(System.getProperty("line.separator"));
        }

        result.append("Match Status : " + getCurrentMatchStatus());

        return result.toString();
    }

    protected String getCurrentScore() {
        StringBuilder score = new StringBuilder();
        for (TennisSet tennisSet : tennisSets) {
            score.append("(" + tennisSet.gameNb1 + "-" + tennisSet.gameNb2 + ")");
        }
        return score.toString();
    }

    protected String getCurrentMatchStatus() {
        if (!hasMatchWinner()) {
            return "in progress";
        } else {
            if (setNb1 > setNb2) {
                return "Player 1 wins";
            } else {
                return "Player 2 wins";
            }
        }
    }

    private boolean isDeuce() {
        return pointNb1 >= 3 && pointNb2 == pointNb1;
    }

    private String playerWithHighestPoints() {
        if (pointNb1 > pointNb2) {
            return name1;
        } else {
            return name2;
        }
    }

    private boolean hasAdvantage() {
        if (pointNb2 >= 4 && pointNb2 == pointNb1 + 1)
            return true;
        if (pointNb1 >= 4 && pointNb1 == pointNb2 + 1)
            return true;

        return false;

    }

    private String translateScore(int score) {
        switch (score) {
            case 3:
                return "40";
            case 2:
                return "30";
            case 1:
                return "15";
            case 0:
                return "0";
        }
        throw new IllegalArgumentException("Illegal score: " + score);
    }

    public String getCurrentStatus() {

        if (hasAdvantage()) {
            return "advantage";
        }

        if (isDeuce())
            return "deuce";

        return translateScore(pointNb1) + "-" + translateScore(pointNb2);
    }
}
