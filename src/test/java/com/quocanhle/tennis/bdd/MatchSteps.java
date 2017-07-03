package com.quocanhle.tennis.bdd;

import com.quocanhle.tennis.IllegalMatchException;
import com.quocanhle.tennis.Match;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

/**
 * Created by quocanh on 29/06/17.
 */
public class MatchSteps {
    Match match;
    String name1;
    String name2;

    @Given("a new tennis match between $name1 and $name2")
    public void createMatch(String name1, String name2) {
        match = new Match(name1, name2);
        this.name1 = name1;
        this.name2 = name2;
    }

    @When("$name scores")
    public void scores(String name) throws IllegalMatchException {
        if (name.equals(name1)) {
            match.playerOneScores();
        } else if (name.equals(name2)) {
            match.playerTwoScores();
        } else {
            Assert.fail("Player " + name + " does not exist!!!");
        }
    }

    @Then("would see $result")
    public void checkResult(String result) {
        Assert.assertTrue(result.equals(match.getResult()));
    }
}
