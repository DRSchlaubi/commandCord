package me.schlaubi.commandcord.command.event.impl;


import me.schlaubi.commandcord.command.event.CommandEvent;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.server.Server;

public class JavacordCommandEvent extends CommandEvent {

    public JavacordCommandEvent(Object message, Object textChannel, Object guild, Object author) {
        super(message, textChannel, guild, author);
    }

    /**
     * Returns the Message Object casted to a Message {@link org.javacord.api.entity.message.Message}
     * @return Message message
     */
    public Message getMessage() {
        return ((Message) message);
    }

    /**
     * Returns the TextChannel Object casted to a Message {@link org.javacord.api.entity.channel.TextChannel}
     * @return TextChannel channel
     */
    public TextChannel getChannel() {
        return ((TextChannel) textChannel);
    }

    /**
     * Returns the Guild Object casted to a Server {@link org.javacord.api.entity.server.Server}
     * @return Server guild
     */
    public Server getServer() {
        return (Server) guild;
    }

    /**
     * Returns the Author Object casted to a MessageAuthor {@link org.javacord.api.entity.message.MessageAuthor}
     * @return MessageAuthor author
     */
    public MessageAuthor getAuthor() {
        return ((MessageAuthor) author);
    }
}
