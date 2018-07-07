package me.schlaubi.commandcord.command.handlers;

import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.permission.Permissions;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public abstract class JDACommandHandler extends GeneralCommandHandler {

    /**
     * Handler for every command
     *
     * @param aliases     String[] All aliases to invoke that command
     * @param type        CommandType Type of the Command
     * @param permissions Permissions The permissions of the command
     * @param description The description of the command
     * @param usage       The usage of that command
     */
    public JDACommandHandler(String[] aliases, CommandType type, Permissions permissions, String description, String usage) {
        super(aliases, type, permissions, description, usage);
    }

    public abstract Message run(CommandInvocation invocation);

    public static class CommandInvocation extends GeneralInvocation {

        private Message message;

        public CommandInvocation(String[] args, Message message, String prefix) {
            super(args, prefix);
            this.message = message;
        }

        public Message getMessage() {
            return message;
        }

        public net.dv8tion.jda.core.entities.Member getMember() {
            return message.getMember();
        }

        public TextChannel getChannel() {
            return message.getTextChannel();
        }

        public Guild getGuild() {
            return message.getGuild();
        }

    }
}
