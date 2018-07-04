package me.schlaubi.commandcord.examples.providers;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class PrefixProvider implements me.schlaubi.commandcord.command.PrefixProvider {

    /* Get prefix by guildId "s" */
    @Override
    public String getPrefix(String s) {
        return "TEST";
    }
}
