package me.schlaubi.commandcord.listeners.discord4j;


import me.schlaubi.commandcord.CommandCord;
import sx.blah.discord.handle.impl.obj.Message;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class Discord4JHelper {

    void parseMessage(Message message) {
        CommandCord.getInstance().parse(message.getContent(), message.getGuild().getStringID(), message.getChannel().getStringID(), message.getStringID(), message.getAuthor().getStringID());
    }
}
