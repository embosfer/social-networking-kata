package com.embosfer.katas.twitter.out;

public class StdOutMessageOutputter implements MessageOutputter {

    @Override
    public void printOut(String message) {
        System.out.println(message);
    }
}
