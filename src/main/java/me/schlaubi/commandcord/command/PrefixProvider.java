package me.schlaubi.commandcord.command;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public interface PrefixProvider {

    String getPrefix(String guildId);

}
