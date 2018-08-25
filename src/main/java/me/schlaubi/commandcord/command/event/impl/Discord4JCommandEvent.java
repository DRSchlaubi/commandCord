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

    /**
     * Returns the Message Object casted to an IMessage {@link sx.blah.discord.handle.obj.IMessage}
     * @return IMessage message
     */
    public IMessage getMessage() {
        return (IMessage) message;
    }

    /**
     * Returns the Channel Object casted to an IChannel {@link sx.blah.discord.handle.obj.IChannel}
     * @return IChannel channel
     */
    public IChannel getChannel() {
        return ((IChannel) textChannel);
    }

    /**
     * Returns the Guild Object casted to an IGuild {@link sx.blah.discord.handle.obj.IGuild}
     * @return IGuild guild
     */
    public IGuild getGuild() {
        return (IGuild) guild;
    }

    /**
     * Returns the Author Object casted to an IUser {@link sx.blah.discord.handle.obj.IUser}
     * @return IUser user
     */
    public IUser getAuthor(){
        return ((IUser) author);
    }

}

