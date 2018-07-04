package me.schlaubi.commandcord.core.parser;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;
import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.PrefixProvider;
import me.schlaubi.commandcord.command.handlers.GeneralCommandHandler;
import me.schlaubi.commandcord.command.handlers.JavaCordHandler;
import me.schlaubi.commandcord.command.permission.Member;
import me.schlaubi.commandcord.command.permission.PermissionProvider;
import me.schlaubi.commandcord.core.CommandParser;
import me.schlaubi.commandcord.event.events.CommandExecutedEvent;
import me.schlaubi.commandcord.event.events.CommandFailedEvent;
import me.schlaubi.commandcord.event.events.NoPermissionEvent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class JavaCordParser extends CommandParser {

    @Override
    public void parse(String message, String guildId, String textChannelId, String messageId) {
        if(!isCommand(message, guildId)) return;
        JavaCordHandler handler = (JavaCordHandler) getHandlerByAlias(getAlias(message, guildId));
        if(handler == null) return;
        JavaCordHandler.CommandInvocation invocation = parseInvocation(message, guildId, textChannelId, messageId, handler);

        /* Delete message if enabled */
        if(CommandCord.getInstance().isDeleteInvokeMessage())
            invocation.getMessage().delete();

        if(!handler.getPermissions().isCovered(Member.fromJavaCord(invocation.getUser(), invocation.getServer()))){
            CommandCord.getInstance().getEventManager().call(new NoPermissionEvent(invocation, handler));
            return;
        }

        try {
            String answer = handler.run(invocation);
            if(answer != null) {
                Message msg = invocation.getChannel().sendMessage(answer).get();
                if(CommandCord.getInstance().getDeleteCommandMessage() != 0)
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            msg.delete();
                        }
                    }, CommandCord.getInstance().getDeleteCommandMessage() * 1000);
            }
        } catch (Exception e){
            CommandCord.getInstance().getEventManager().call(new CommandFailedEvent(invocation, handler, e));
        }
        CommandCord.getInstance().getEventManager().call(new CommandExecutedEvent(invocation, handler));
    }

    private JavaCordHandler.CommandInvocation parseInvocation(String message, String guildId, String textChannelId, String messageId, JavaCordHandler handler){
        return new JavaCordHandler.CommandInvocation(getArgs(message, guildId), getMessageById(guildId, textChannelId, messageId, handler), getAlias(message, guildId));
    }

    private Message getMessageById(String guildId, String channelId, String messageId, GeneralCommandHandler handler){
        try {
            return getChannelById(guildId, channelId).getMessageById(messageId).get();
        } catch (InterruptedException | ExecutionException e) {
            CommandCord.getInstance().getEventManager().call(new CommandFailedEvent(null, handler, e));
            return null;
        }
    }

    private Channel getChannelById(String guildId, String channelId){
        return getGuildById(guildId).getChannelById(channelId);
    }

    private Server getGuildById(String guildId){
        DiscordAPI api = (DiscordAPI) CommandCord.getInstance().getApi();
        return api.getServerById(guildId);
    }
}
