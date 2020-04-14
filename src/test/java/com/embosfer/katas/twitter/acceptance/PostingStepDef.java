package com.embosfer.katas.twitter.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PostingStepDef {

    private static final String EXIT_MESSAGE = "Bye!";
    private static String TWITTER_CONSOLE_ON_COMMAND_LINE =
            "/usr/bin/java -cp ./target/classes " +
                    "com.embosfer.katas.twitter.Main";

    private Process consoleProcess;
    private PrintWriter console;
    private BufferedReader screen;

    @Given("A Twitter console")
    public void aTwitterConsole() throws IOException {
        consoleProcess = Runtime.getRuntime().exec(TWITTER_CONSOLE_ON_COMMAND_LINE);
        console = new PrintWriter(consoleProcess.getOutputStream());
        screen = new BufferedReader(new InputStreamReader(consoleProcess.getInputStream()));
    }

    @When("{string} posts the messages")
    public void userPostsTheMessages(String user, List<String> messages) {
        messages.forEach(message -> command(user + " -> " + message));
    }

    @When("{string} reads {string} timeline")
    public void readsTimeline(String readingUser, String timelineUser) {
        command(timelineUser);
    }

    @When("{string} follows {string}")
    public void userFollows(String user, String userToFollow) {
        command(user + " follows " + userToFollow);
    }

    private void command(String command) {
        console.write(command + "\n");
        console.flush();
    }

    @Then("These messages appear on the console")
    public void theseMessagesAppearOnTheConsole(List<String> messages) throws IOException {
        command("quit"); // must quit so the java Process terminates. After that we can grab the output

        String outputLine;
        Collection<String> output = new ArrayList<>();
        while ((outputLine = screen.readLine()) != null) {
            output.add(outputLine);
        }
        assertThat(output).isEqualTo(expected(messages));
    }

    private List<String> expected(List<String> messages) {
        List<String> expected = new ArrayList<>(messages);
        expected.add("Bye!");
        return expected;
    }

}
