package me.schlaubi.commandcord.core.parser;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.PrefixProvider;
import me.schlaubi.commandcord.command.handlers.JDACommandHandler;
import me.schlaubi.commandcord.command.permission.Member;
import me.schlaubi.commandcord.command.permission.PermissionProvider;
import me.schlaubi.commandcord.core.CommandManager;
import me.schlaubi.commandcord.core.CommandParser;
import me.schlaubi.commandcord.event.events.CommandExecutedEvent;
import me.schlaubi.commandcord.event.events.CommandFailedEvent;
import me.schlaubi.commandcord.event.events.NoPermissionEvent;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class JDAParser extends CommandParser {

    @Override
    public void parse(String message, String guildId, String textChannelId, String messageId){
        if(!isCommand(message, guildId)) return;
        JDACommandHandler handler = (JDACommandHandler) getHandlerByAlias(getAlias(message, guildId));
        if(handler == null) return;
        JDACommandHandler.CommandInvocation invocation = parseInvocation(message, guildId, textChannelId, messageId);

        if(!handler.getPermissions().isCovered(Member.fromJDA(invocation.getMember()))){
            CommandCord.getInstance().getEventManager().call(new NoPermissionEvent(invocation, handler));
            return;
        }

        try {
            Message answer = handler.run(invocation);
            if(answer != null)
                invocation.getChannel().sendMessage(answer).queue();
        } catch (Exception e){
            CommandCord.getInstance().getEventManager().call(new CommandFailedEvent(invocation, handler, e));
        }
        CommandCord.getInstance().getEventManager().call(new CommandExecutedEvent(invocation, handler));

    }

    private JDACommandHandler.CommandInvocation parseInvocation(String message, String guildId, String textChannelId, String messageId){
        return new JDACommandHandler.CommandInvocation(getArgs(message,guildId), getMessageById(messageId, textChannelId, guildId), getAlias(message, guildId));
    }

    private Message getMessageById(String messageId, String textChannelId, String guildId){
        return getTextChannel(textChannelId,guildId).getMessageById(messageId).complete();
    }

    private TextChannel getTextChannel(String textChannelId, String guildId){
        return getGuildById(guildId).getTextChannelById(textChannelId);
    }

    private Guild getGuildById(String guildId){
        Object obj = CommandCord.getInstance().getApi();
        if(obj instanceof JDA)
            return ((JDA) obj).getGuildById(guildId);
        else
            return ((ShardManager) obj).getGuildById(guildId);
    }
}
