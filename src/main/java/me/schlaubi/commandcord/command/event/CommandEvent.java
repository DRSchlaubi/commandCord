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

    public Object getMessageObject() {
        return message;
    }

    public Object getTextChannelObject() {
        return textChannel;
    }

    public Object getGuildObject() {
        return guild;
    }

    public Object getAuthorObject() {
        return author;
    }
}
