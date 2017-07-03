package com.quocanhle.tennis;

/**
 * Created by quocanh on 03/07/2017.
 */
public class IllegalMatchException extends Exception {

    public String toString() {
        return "The match has already finished!!!";
    }
}
