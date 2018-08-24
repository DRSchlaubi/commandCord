package me.schlaubi.commandcord.listeners.javacord;

import me.schlaubi.commandcord.CommandCord;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class JavaCordListener implements MessageCreateListener {

    void parseMessage(Message message) {
        CommandCord.getInstance().parse(message.getContent(), message.getServer().get().getIdAsString(), message.getChannel().getIdAsString(), message.getIdAsString(), message.getAuthor().getIdAsString());
    }

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        parseMessage(messageCreateEvent.getMessage());
    }
}
