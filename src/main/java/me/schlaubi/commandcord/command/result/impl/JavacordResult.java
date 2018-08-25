package me.schlaubi.commandcord.command.result.impl;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.result.Result;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Use this class when you're using the Javacord library
 */
public class JavacordResult implements Result {

    private EmbedBuilder embedBuilder;
    private String message;
    private CompletableFuture<Message> action = null;

    @Override
    public void sendMessage(Object textChannel, Object guild) {
        TextChannel channel = ((TextChannel) textChannel);

        if (embedBuilder == null)
            action = channel.sendMessage(message);
        else
            action = channel.sendMessage("", embedBuilder);
        if (CommandCord.getInstance().getDeleteCommandMessage() != 0)
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        action.get().delete();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }, CommandCord.getInstance().getDeleteCommandMessage());
    }

    /**
     * Creates a Result from a String
     * @param message The content of the Message
     */
    public JavacordResult(String message) {
        this.message = message;
    }

    /**
     * Creates a Result from an EmbedBuilder {@link org.javacord.api.entity.message.embed.EmbedBuilder}
     * @param embedBuilder The EmbedBuilder
     */
    public JavacordResult(EmbedBuilder embedBuilder) {
        this.embedBuilder = embedBuilder;
    }
}
