package me.schlaubi.commandcord.listeners.discord4j;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageEditEvent;
import sx.blah.discord.handle.impl.obj.Message;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class Discord4JEditsListener extends Discord4JHelper  {

    @EventSubscriber
    public void onMessageEdit(MessageEditEvent messageEditEvent) {
        parseMessage((Message) messageEditEvent.getMessage());
    }
}
