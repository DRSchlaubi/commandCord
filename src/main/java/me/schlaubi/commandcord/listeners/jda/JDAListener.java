package me.schlaubi.commandcord.listeners.jda;

import me.schlaubi.commandcord.CommandCord;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class JDAListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        parseCommand(event.getMessage());
    }

    void parseCommand(Message message) {
        CommandCord.getInstance().parse(message.getContentDisplay(), message.getGuild().getId(), message.getTextChannel().getId(), message.getId(), message.getAuthor().getId());
    }
}
