package me.schlaubi.commandcord.command.handlers;

import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.permission.Permissions;
import sx.blah.discord.handle.impl.obj.Channel;
import sx.blah.discord.handle.impl.obj.Guild;
import sx.blah.discord.handle.impl.obj.Message;
import sx.blah.discord.handle.impl.obj.User;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public abstract class Discord4JCommandHandler extends GeneralCommandHandler {

    /**
     * Handler for every command
     *
     * @param aliases     String[] All aliases to invoke that command
     * @param type        CommandType Type of the Command
     * @param permissions Permissions The permissions of the command
     * @param description The description of the command
     * @param usage       The usage of that command
     */
    public Discord4JCommandHandler(String[] aliases, CommandType type, Permissions permissions, String description, String usage) {
        super(aliases, type, permissions, description, usage);
    }

    public abstract String run(CommandInvocation invocation);

    public static class CommandInvocation extends GeneralInvocation {

        private Message message;

        public CommandInvocation(String[] args, Message message, String prefix) {
            super(args, prefix);
            this.message = message;
        }

        public Message getMessage() {
            return message;
        }

        public User getUser() {
            return (User) message.getAuthor();
        }

        public Channel getChannel() {
            return (Channel) message.getChannel();
        }

        public Guild getGuild() {
            return (Guild) message.getGuild();
        }
    }
}
