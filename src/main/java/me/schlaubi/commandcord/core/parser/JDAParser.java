package me.schlaubi.commandcord.core.parser;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.event.impl.JDACommandEvent;
import me.schlaubi.commandcord.command.result.Result;
import me.schlaubi.commandcord.core.CommandParser;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

public class JDAParser extends CommandParser {

    @Override
    protected CommandEvent parseEvent(String message, String guildId, String textChannelId, String messageId) {
        Message invokeMessage = getMessageById(messageId, textChannelId, guildId);
        return new JDACommandEvent(invokeMessage, invokeMessage.getTextChannel(), invokeMessage.getGuild(), invokeMessage.getAuthor());
    }

    @Override
    protected void deleteInvokeMessage(String messageId, String guildId, String textChannelId) {
        getMessageById(messageId, textChannelId, guildId).delete().queue();
    }

    @Override
    protected void sendMessage(Result result, String guildId, String textChannelId) throws Exception {
        Guild guild= getGuildById(guildId);
        result.sendMessage(guild.getTextChannelById(textChannelId), guild);
    }

    @Override
    protected void sendTyping(String guildId, String textChannelId) {
        getTextChannelById(textChannelId, guildId).sendTyping().queue();
    }

    private Message getMessageById(String messageId, String textChannelId, String guildId) {
        return getTextChannelById(textChannelId, guildId).getMessageById(messageId).complete();
    }

    private TextChannel getTextChannelById(String textChannelId, String guildId) {
        return getGuildById(guildId).getTextChannelById(textChannelId);
    }

    private Guild getGuildById(String guildId) {
        Object obj = CommandCord.getInstance().getApi();
        if (obj instanceof JDA)
            return ((JDA) obj).getGuildById(guildId);
        else
            return ((ShardManager) obj).getGuildById(guildId);
    }
}
