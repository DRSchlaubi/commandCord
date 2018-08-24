package me.schlaubi.commandcord.core.parser;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.event.impl.Discord4JCommandEvent;
import me.schlaubi.commandcord.command.result.Result;
import me.schlaubi.commandcord.core.CommandParser;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.obj.Guild;
import sx.blah.discord.handle.obj.IMessage;

public class Discord4JParser extends CommandParser {

    @Override
    protected CommandEvent parseEvent(String message, String guildId, String textChannelId, String messageId) {
        IMessage invokeMessage = getGuildById(guildId).getMessageByID(Long.parseLong(messageId));
        return new Discord4JCommandEvent(invokeMessage, invokeMessage.getChannel(), invokeMessage.getGuild(), invokeMessage.getAuthor());
    }

    @Override
    protected void deleteInvokeMessage(String messageId, String guildId, String textChannelId) {
        getGuildById(guildId).getMessageByID(Long.parseLong(messageId)).delete();
    }

    @Override
    protected void sendMessage(Result result, String guildId, String textChannelId) throws Exception {

    }

    @Override
    protected void sendTyping(String guildId, String textChannelId) {
        getGuildById(guildId).getChannelByID(Long.parseLong(textChannelId)).toggleTypingStatus();
    }

    private Guild getGuildById(String guildId) {
        IDiscordClient client = (IDiscordClient) CommandCord.getInstance().getApi();
        return (Guild) client.getGuildByID(Long.parseLong(guildId));
    }
}
