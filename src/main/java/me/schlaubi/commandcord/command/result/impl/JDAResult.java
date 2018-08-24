package me.schlaubi.commandcord.command.result.impl;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.result.Result;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;

import java.util.concurrent.TimeUnit;

public class JDAResult implements Result {

    private Message message;

    @Override
    public void sendMessage(Object textChannel, Object guild) {
        TextChannel channel = (TextChannel) textChannel;
        if (channel.getGuild().getSelfMember().hasPermission(channel, Permission.MESSAGE_WRITE))
            ((TextChannel) textChannel).sendMessage(message).queue(msg -> {
                if(CommandCord.getInstance().getDeleteCommandMessage() != 0)
                    msg.delete().reason("Automated command response deletion").queueAfter(CommandCord.getInstance().getDeleteCommandMessage(), TimeUnit.SECONDS);
            });
    }

    public JDAResult(Message message) {
        this.message = message;
    }

    public JDAResult(String message) {
        this(new MessageBuilder().setContent(message).build());
    }

    public JDAResult(MessageEmbed embed) {
        this(new MessageBuilder().setEmbed(embed).build());
    }

    public JDAResult(EmbedBuilder builder) {
        this(builder.build());
    }
}
