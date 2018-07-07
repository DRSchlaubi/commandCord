package me.schlaubi.commandcord.command;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public interface PrefixProvider {

    /**
     * Will be called when guild specified prefixes are enabled
     *
     * @param guildId Id of the guild where the command is executed
     * @return The prefix
     */
    String getPrefix(String guildId);

}
