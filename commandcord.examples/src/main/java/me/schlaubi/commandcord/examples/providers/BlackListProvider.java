package me.schlaubi.commandcord.examples.providers;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class BlackListProvider implements me.schlaubi.commandcord.command.BlackListProvider {

    /* Block the channel with the id 454682750981636127*/
    @Override
    public boolean isBlackListed(String s) {
        return s.equals("454682750981636127");
    }
}
