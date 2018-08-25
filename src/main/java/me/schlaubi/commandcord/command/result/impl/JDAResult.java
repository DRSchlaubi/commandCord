package me.schlaubi.commandcord.command.result.impl;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.result.Result;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Use this class when you're using the JDA library
 */
public class JDAResult implements Result {

    private Message message;
    private final Logger logger = Logger.getLogger(Result.class);

    @Override
    public void sendMessage(Object textChannel, Object guild) {
        TextChannel channel = (TextChannel) textChannel;
        if (channel.getGuild().getSelfMember().hasPermission(channel, Permission.MESSAGE_WRITE))
            ((TextChannel) textChannel).sendMessage(message).queue(msg -> {
                if(CommandCord.getInstance().getDeleteCommandMessage() != 0)
                    msg.delete().reason("Automated command response deletion").queueAfter(CommandCord.getInstance().getDeleteCommandMessage(), TimeUnit.SECONDS);
            });
        else
            logger.warn(String.format("Warning: Invoke message could not be sent due to an permission error missing permission: %s Guild: %s", Permission.MESSAGE_WRITE, channel.getGuild().getId()));

    }

    /**
     * Creates an Result from a Message Object of the MessageBuilder {@link net.dv8tion.jda.core.MessageBuilder}
     * @param message Message object from the builder
     */
    public JDAResult(Message message) {
        this.message = message;
    }

    /**
     * Creates an Result from a String
     * @param message The content of the Message
     */
    public JDAResult(String message) {
        this(new MessageBuilder().setContent(message).build());
    }

    /**
     * Creates an Result from an Embed of the EmbedBuilder {@link net.dv8tion.jda.core.EmbedBuilder}
     * @param embed The built embed
     */
    public JDAResult(MessageEmbed embed) {
        this(new MessageBuilder().setEmbed(embed).build());
    }

    /**
     * Creates an Result from an EmbedBuilder
     * @param builder The embed builder
     */
    public JDAResult(EmbedBuilder builder) {
        this(builder.build());
    }
}
