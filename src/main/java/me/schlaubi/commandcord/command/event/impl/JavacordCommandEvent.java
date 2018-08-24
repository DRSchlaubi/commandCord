package me.schlaubi.commandcord.command.event.impl;


import me.schlaubi.commandcord.command.event.CommandEvent;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

public class JavacordCommandEvent extends CommandEvent {

    public JavacordCommandEvent(Object message, Object textChannel, Object guild, Object author) {
        super(message, textChannel, guild, author);
    }

    public Message getMessage() {
        return ((Message) message);
    }

    public TextChannel getChannel() {
        return ((TextChannel) textChannel);
    }

    public Server getServer() {
        return (Server) guild;
    }

    public User getAuthor() {
        return ((User) author);
    }
}
