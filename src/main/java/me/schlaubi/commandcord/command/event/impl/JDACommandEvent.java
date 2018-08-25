package me.schlaubi.commandcord.command.event.impl;

import me.schlaubi.commandcord.command.event.CommandEvent;
import net.dv8tion.jda.core.entities.*;

public class JDACommandEvent extends CommandEvent {

    public JDACommandEvent(Object message, Object textChannel, Object guild, Object author) {
        super(message, textChannel, guild, author);
    }

    /**
     * Returns the Message Object casted to a message {@link net.dv8tion.jda.core.entities.Message}
     * @return Message message
     */
    public Message getMessage() {
        return (Message) message;
    }

    /**
     * Returns the TextChannel Object casted to a TextChannel {@link net.dv8tion.jda.core.entities.TextChannel}
     * @return TextChannel channel
     */
    public TextChannel getTextChannel() {
        return ((TextChannel) textChannel);
    }

    /**
     * Returns the Guild Object casted to a Guild {@link net.dv8tion.jda.core.entities.Guild}
     * @return Guild guild
     */
    public Guild getGuild() {
        return ((Guild) guild);
    }

    /**
     * Returns the Author Object casted to an User {@link net.dv8tion.jda.core.entities.User}
     * @return User author
     */
    public User getAuthor() {
        return ((User) author);
    }

    /**
     * Returns the Member Object {@link net.dv8tion.jda.core.entities.Member} of the User Object
     * @return Member author
     */
    public Member getMember() {
        return getGuild().getMember(getAuthor());
    }
}
