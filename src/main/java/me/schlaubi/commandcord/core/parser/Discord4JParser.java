package me.schlaubi.commandcord.core.parser;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.PrefixProvider;
import me.schlaubi.commandcord.command.handlers.Discord4JCommandHandler;
import me.schlaubi.commandcord.command.permission.Member;
import me.schlaubi.commandcord.command.permission.PermissionProvider;
import me.schlaubi.commandcord.core.CommandManager;
import me.schlaubi.commandcord.core.CommandParser;
import me.schlaubi.commandcord.event.events.CommandExecutedEvent;
import me.schlaubi.commandcord.event.events.CommandFailedEvent;
import me.schlaubi.commandcord.event.events.NoPermissionEvent;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.obj.Guild;
import sx.blah.discord.handle.impl.obj.Message;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class Discord4JParser extends CommandParser {

    @Override
    public void parse(String message, String guildId, String textChannelId, String messageId) {
        if(!isCommand(message, guildId)) return;
        Discord4JCommandHandler handler = (Discord4JCommandHandler) getHandlerByAlias(getAlias(message, guildId));
        if (handler == null) return;
        Discord4JCommandHandler.CommandInvocation invocation = parseInvocation(message, guildId, textChannelId, messageId);

        if(!handler.getPermissions().isCovered(Member.fromDiscord4J(invocation.getUser(), invocation.getGuild()))){
            CommandCord.getInstance().getEventManager().call(new NoPermissionEvent(invocation, handler));
            return;
        }

        try{
            String answer = handler.run(invocation);
            if(answer != null)
                invocation.getChannel().sendMessage(answer);
        }  catch (Exception e){
            CommandCord.getInstance().getEventManager().call(new CommandFailedEvent(invocation, handler, e));
        }
        CommandCord.getInstance().getEventManager().call(new CommandExecutedEvent(invocation, handler));
    }

    private Discord4JCommandHandler.CommandInvocation parseInvocation(String message, String guildId, String textChannelId, String messageId){
        return new Discord4JCommandHandler.CommandInvocation(getArgs(message,guildId), getMessageById(messageId, guildId), getAlias(message, guildId));
    }

    public Message getMessageById(String guildId, String messageId){
        return (Message) getGuildById(guildId).getMessageByID(Long.parseLong(messageId));
    }

    private Guild getGuildById(String guildId){
        IDiscordClient client = (IDiscordClient) CommandCord.getInstance().getApi();
        return (Guild) client.getGuildByID(Long.parseLong(guildId));
    }

}
