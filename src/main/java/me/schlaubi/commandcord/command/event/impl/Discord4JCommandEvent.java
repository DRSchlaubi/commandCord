package me.schlaubi.commandcord.command.event.impl;

import me.schlaubi.commandcord.command.event.CommandEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class Discord4JCommandEvent extends CommandEvent {

    public Discord4JCommandEvent(Object message, Object textChannel, Object guild, Object author) {
        super(message, textChannel, guild, author);
    }

    public IMessage getMessage() {
        return (IMessage) message;
    }

    public IChannel getChannel() {
        return ((IChannel) textChannel);
    }

    public IGuild getGuild() {
        return (IGuild) guild;
    }

    public IUser getAuthor(){
        return ((IUser) author);
    }

}

