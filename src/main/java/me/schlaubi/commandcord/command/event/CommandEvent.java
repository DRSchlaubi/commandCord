package me.schlaubi.commandcord.command.event;

public class CommandEvent {

    protected Object message;
    protected Object textChannel;
    protected Object guild;
    protected Object author;

    public CommandEvent(Object message, Object textChannel, Object guild, Object author) {
        this.message = message;
        this.textChannel = textChannel;
        this.guild = guild;
        this.author = author;
    }

    /**
     * Returns the message Object
     * @return Object message
     */
    public Object getMessageObject() {
        return message;
    }

    /**
     * Returns the Channel object
     * @return Object channel
     */
    public Object getTextChannelObject() {
        return textChannel;
    }

    /**
     * Returns the guild object
     * @return Object guild
     */
    public Object getGuildObject() {
        return guild;
    }

    /**
     * Returns the author object
     * @return Object author
     */
    public Object getAuthorObject() {
        return author;
    }
}
