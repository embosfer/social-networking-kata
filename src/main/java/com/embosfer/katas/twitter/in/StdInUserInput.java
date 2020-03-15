package com.embosfer.katas.twitter.in;

import java.util.Scanner;

public class StdInUserInput implements UserInput {

    private final Scanner in = new Scanner(System.in);

    @Override
    public String nextInput() {
        return in.nextLine();
    }
}
