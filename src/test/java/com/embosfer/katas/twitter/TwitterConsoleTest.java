package com.embosfer.katas.twitter;

import com.embosfer.katas.twitter.commands.Command;
import com.embosfer.katas.twitter.commands.TwitterCommandProcessor;
import com.embosfer.katas.twitter.in.UserInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TwitterConsoleTest {

    UserInput userInput = mock(UserInput.class);
    TwitterCommandProcessor commandProcessor = mock(TwitterCommandProcessor.class);

    @Test
    void commandsArePassedOverToCommandProcessor() {

        TwitterConsole twitter = new TwitterConsole(userInput, commandProcessor);

        when(userInput.nextInput()).thenReturn("a-user-command");

        twitter.start();

        verify(commandProcessor).process("a-user-command");
    }
}