package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.domain.Message;
import com.embosfer.katas.twitter.domain.User;
import com.embosfer.katas.twitter.out.MessageOutputter;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static java.time.temporal.ChronoUnit.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ReadUserTimelineCommandTest {

    MessageOutputter messageOutputter = mock(MessageOutputter.class);

    @Test
    void canPrintUserTimeline() {
        Instant now = Instant.EPOCH;
        User user = User.of("Alice");
        List<Message> alicesMessages = List.of(
                Message.of("1st message", user, now.minus(999, MILLIS)),
                Message.of("2nd message", user, now.minus(59, SECONDS)),
                Message.of("3rd message", user, Instant.EPOCH.minus(1, MINUTES)),
                Message.of("4th message", user, Instant.EPOCH.minus(61, SECONDS)),
                Message.of("5th message", user, Instant.EPOCH.minus(59, MINUTES)),
                Message.of("6th message", user, Instant.EPOCH.minus(1, HOURS)),
                Message.of("7th message", user, Instant.EPOCH.minus(23, HOURS)),
                Message.of("8th message", user, Instant.EPOCH.minus(24, HOURS)));
        ReadUserTimelineCommand command = new ReadUserTimelineCommand(user, alicesMessages, now, messageOutputter);

        command.execute();

        verify(messageOutputter).printOut("Alice");
        verify(messageOutputter).printOut("1st message (0 seconds ago)");
        verify(messageOutputter).printOut("2nd message (59 seconds ago)");
        verify(messageOutputter).printOut("3rd message (1 minutes ago)");
        verify(messageOutputter).printOut("4th message (1 minutes ago)");
        verify(messageOutputter).printOut("5th message (59 minutes ago)");
        verify(messageOutputter).printOut("6th message (1 hours ago)");
        verify(messageOutputter).printOut("7th message (23 hours ago)");
        verify(messageOutputter).printOut("8th message (1 days ago)");
    }

}