package me.schlaubi.commandcord.command;

import me.schlaubi.commandcord.command.handlers.GeneralCommandHandler;
import me.schlaubi.commandcord.command.handlers.GeneralInvocation;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class DefaultBeforeTasks implements BeforeTasks{

    @Override
    public boolean run(String message, String guildId, String textChannelId, String messageId) {
        return true;
    }
}
