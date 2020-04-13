package com.embosfer.katas.twitter.commands;

import com.embosfer.katas.twitter.domain.User;

public interface UserCommand extends Command {

    User user();
}
