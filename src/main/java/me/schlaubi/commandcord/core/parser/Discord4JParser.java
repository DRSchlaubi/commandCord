package me.schlaubi.commandcord.core.parser;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.event.impl.Discord4JCommandEvent;
import me.schlaubi.commandcord.command.result.Result;
import me.schlaubi.commandcord.core.CommandParser;
import org.apache.log4j.Logger;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.obj.Guild;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.PermissionUtils;



public class Discord4JParser extends CommandParser {

    @Override
    protected CommandEvent parseEvent(String message, String guildId, String textChannelId, String messageId) {
        IMessage invokeMessage = getGuildById(guildId).getMessageByID(Long.parseLong(messageId));
        return new Discord4JCommandEvent(invokeMessage, invokeMessage.getChannel(), invokeMessage.getGuild(), invokeMessage.getAuthor());
    }

    @Override
    protected void deleteInvokeMessage(String messageId, String guildId, String textChannelId) {
        IChannel channel = getGuildById(guildId).getChannelByID(Long.parseLong(textChannelId));
        if (PermissionUtils.hasPermissions(channel.getGuild(), ((IDiscordClient) CommandCord.getInstance().getApi()).getOurUser() , Permissions.MANAGE_MESSAGES))
            channel.getMessageByID(Long.parseLong(messageId)).delete();
        else
            logger.warn(String.format("Warning: Invoke message could not be deleted due to an permission error missing permission: %s Guild: %s", Permissions.MANAGE_MESSAGES, guildId));

    }

    @Override
    protected void sendMessage(Result result, String guildId, String textChannelId) throws Exception {
        IChannel channel = getGuildById(guildId).getChannelByID(Long.parseLong(textChannelId));
        if (PermissionUtils.hasPermissions(channel.getGuild(), ((IDiscordClient) CommandCord.getInstance().getApi()).getOurUser() , Permissions.SEND_MESSAGES))
            result.sendMessage(channel, channel.getGuild());
        logger.warn(String.format("Warning: Invoke message could not be sent due to an permission error missing permission: %s Guild: %s", Permissions.SEND_MESSAGES, guildId));

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
