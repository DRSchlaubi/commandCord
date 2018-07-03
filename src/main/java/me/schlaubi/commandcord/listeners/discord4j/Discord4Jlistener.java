package me.schlaubi.commandcord.listeners.discord4j;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.obj.Message;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class Discord4Jlistener extends Discord4JHelper implements IListener<MessageReceivedEvent> {

    @Override
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        parseMessage((Message) messageReceivedEvent.getMessage());
    }


}
