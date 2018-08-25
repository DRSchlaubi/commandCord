package me.schlaubi.commandcord.listeners.discord4j;


import me.schlaubi.commandcord.CommandCord;
import sx.blah.discord.handle.impl.obj.Message;
import sx.blah.discord.handle.impl.obj.PrivateChannel;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class Discord4JHelper {

    void parseMessage(Message message) {
        if (!message.getAuthor().isBot() && !(message.getChannel() instanceof PrivateChannel))
            CommandCord.getInstance().parse(message.getContent(), message.getGuild().getStringID(), message.getChannel().getStringID(), message.getStringID(), message.getAuthor().getStringID());
    }
}
