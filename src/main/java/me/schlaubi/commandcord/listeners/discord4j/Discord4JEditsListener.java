package me.schlaubi.commandcord.listeners.discord4j;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageEditEvent;
import sx.blah.discord.handle.impl.obj.Message;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class Discord4JEditsListener extends Discord4JHelper implements IListener<MessageEditEvent> {

    @Override
    public void handle(MessageEditEvent messageEditEvent) {
        parseMessage((Message) messageEditEvent.getMessage());
    }
}
