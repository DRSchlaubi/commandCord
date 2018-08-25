package me.schlaubi.commandcord.listeners.discord4j;

import net.dv8tion.jda.core.entities.PrivateChannel;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.obj.Message;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class Discord4JListener extends Discord4JHelper {

    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent messageReceivedEvent) {
        parseMessage((Message) messageReceivedEvent.getMessage());
    }


}
