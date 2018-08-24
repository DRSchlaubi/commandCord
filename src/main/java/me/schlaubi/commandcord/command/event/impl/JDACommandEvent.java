package me.schlaubi.commandcord.command.event.impl;

import me.schlaubi.commandcord.command.event.CommandEvent;
import net.dv8tion.jda.core.entities.*;

public class JDACommandEvent extends CommandEvent {

    public JDACommandEvent(Object message, Object textChannel, Object guild, Object author) {
        super(message, textChannel, guild, author);
    }

    public Message getMessage() {
        return (Message) message;
    }

    public TextChannel getTextChannel() {
        return ((TextChannel) textChannel);
    }

    public Guild getGuild() {
        return ((Guild) guild);
    }

    public User getAuthor() {
        return ((User) author);
    }

    public Member getMember() {
        return getGuild().getMember(getAuthor());
    }
}
