package me.schlaubi.commandcord.command;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class DefaultBeforeTasks implements BeforeTasks {

    @Override
    public boolean run(String message, String guildId, String textChannelId, String messageId) {
        return true;
    }
}
