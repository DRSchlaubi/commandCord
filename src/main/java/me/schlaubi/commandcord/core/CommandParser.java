package me.schlaubi.commandcord.core;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.handlers.Command;
import me.schlaubi.commandcord.command.permission.Member;
import me.schlaubi.commandcord.command.result.Result;
import me.schlaubi.commandcord.event.events.CommandExecutedEvent;
import me.schlaubi.commandcord.event.events.CommandFailedEvent;
import me.schlaubi.commandcord.event.events.NoPermissionEvent;
import org.apache.log4j.Logger;

public abstract class CommandParser {

    protected final Logger logger = Logger.getLogger(CommandParser.class);


    public void parse(String message, String guildId, String textChannelId, String messageId, String authorId) {
        if (isNoCommand(message, guildId)) return;
        Command command = getCommandByAlias(getAlias(message, guildId));
        if (command == null) return;
        if (!CommandCord.getInstance().getBeforeTasksHandler().run(message, guildId, textChannelId, messageId))
            return;
        if (CommandCord.getInstance().useBlacklist)
            if (CommandCord.getInstance().getBlackListProvider().isBlackListed(textChannelId, guildId))
                return;
        if (CommandCord.getInstance().isTyping())
            sendTyping(guildId, textChannelId);
        String[] args = getArgs(message, guildId);
        if (args.length > 0)
            if (command.getSubCommandAssociations().containsKey(args[0]))
                command = command.getSubCommandAssociations().get(args[0]);
        CommandEvent event = parseEvent(message, guildId, textChannelId, messageId);
        /* Delete message if enabled */
        if (CommandCord.getInstance().isDeleteInvokeMessage())
            deleteInvokeMessage(messageId, guildId, textChannelId);

        /* Check permissions */
        if (!command.getPermissions().isCovered(new Member(authorId, guildId))) {
            CommandCord.getInstance().getEventManager().call(new NoPermissionEvent(command, event));
            return;
        }
        try {
            sendMessage(command.run(args, event), guildId, textChannelId);
        } catch (Exception e) {
            CommandCord.getInstance().getEventManager().call(new CommandFailedEvent(command, event, e));
        }
        CommandCord.getInstance().getEventManager().call(new CommandExecutedEvent(command, event));

    }

    protected abstract CommandEvent parseEvent(String message, String guildId, String textChannelId, String messageId);

    protected abstract void deleteInvokeMessage(String messageId, String guildId, String textChannelId);

    protected abstract void sendMessage(Result result, String guildId, String textChannelId) throws Exception;

    protected abstract void sendTyping(String guildId, String textChannelId);

    /**
     * Checks if a message is a command (starts with the right prefix)
     * @param message The content of the message
     * @param guildId The ID of the guild
     * @return If the message is a command
     */
    protected boolean isNoCommand(String message, String guildId) {
        CommandManager instance = CommandCord.getInstance();
        if (instance.useGuildPrefixes)
            return !message.startsWith(instance.defaultPrefix) && !message.startsWith(instance.prefixProvider.getPrefix(guildId));
        else
            return !message.startsWith(instance.defaultPrefix);
    }

    /**
     * Split arguments
     * @param message The content of the message
     * @param guildId The ID of the guild
     * @return The arguments
     */
    protected String[] getArgs(String message, String guildId) {
        String rawArgs = replacePrefix(message, guildId);
        String[] out = rawArgs.replaceFirst(getAlias(message, guildId), "").replaceFirst(" ", "").split(" ");
        if (out[0].equals(""))
            out = new String[]{};
        return out;
    }

    /**
     * Gets the used alias of the message
     * @param message The content of the message
     * @param guildId The ID of the message
     * @return Return the main alias of the command
     */
    protected String getAlias(String message, String guildId) {
        return replacePrefix(message, guildId).split(" ")[0];
    }

    /**
     * Replaces the message
     * @param message The content of the message
     * @param guildId The ID of the guild
     * @return The invoke message without the prefix
     */
    protected String replacePrefix(String message, String guildId) {
        CommandManager instance = CommandCord.getInstance();
        if (instance.useGuildPrefixes) {
            if (message.startsWith(instance.defaultPrefix))
                message = message.replaceFirst(instance.defaultPrefix, "");
            else
                message = message.replaceFirst(instance.prefixProvider.getPrefix(guildId), "");
        } else
            message = message.replaceFirst(instance.defaultPrefix, "");
        return message;
    }

    /**
     * Get the commandHandler by the used alias
     * @param alias The alias of the command
     * @return The Command with the defined alias
     */
    protected Command getCommandByAlias(String alias) {
        return CommandCord.getInstance().commandAssociations.get(alias.toLowerCase());
    }


}
