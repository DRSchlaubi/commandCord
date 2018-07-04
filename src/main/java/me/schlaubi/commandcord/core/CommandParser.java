package me.schlaubi.commandcord.core;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.handlers.GeneralCommandHandler;

import java.util.Arrays;

public abstract class CommandParser {

    public abstract void parse(String message, String guildId, String textChannelId, String messageId);

    protected boolean isCommand(String message, String guildId){
        CommandManager instance = CommandCord.getInstance();
        if(instance.useGuildPrefixes)
            return message.startsWith(instance.defaultPrefix) || message.startsWith(instance.prefixProvider.getPrefix(guildId));
        else
            return message.startsWith(instance.defaultPrefix);
    }

    protected String[] getArgs(String message, String guildId){
        String rawArgs = replacePrefix(message, guildId);
        String[] out = rawArgs.replaceFirst(getAlias(message, guildId), "").replaceFirst(" ", "").split(" ");
        if(out[0].equals(""))
            out = new String[] {};
        return out;
    }

    protected String getAlias(String message, String guildId){
        return replacePrefix(message,guildId).split(" ")[0];
    }

    protected String replacePrefix(String message, String guildId){
        CommandManager instance = CommandCord.getInstance();
        if(instance.useGuildPrefixes) {
            if(message.startsWith(instance.defaultPrefix))
                message = message.replaceFirst(instance.defaultPrefix, "");
            else
                message = message.replaceFirst(instance.prefixProvider.getPrefix(guildId), "");
        } else
            message = message.replaceFirst(instance.defaultPrefix, "");
        return message;
    }

    protected GeneralCommandHandler getHandlerByAlias(String alias){
        return CommandCord.getInstance().commandAssociations.get(alias.toLowerCase());
    }



}
