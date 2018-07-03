package me.schlaubi.commandcord.command.handlers;

import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.permission.Permissions;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public abstract class JavaCordHandler extends GeneralCommandHandler {

    /**
     * Handler for every command
     *
     * @param aliases     String[] All aliases to invoke that command
     * @param type        CommandType Type of the Command
     * @param permissions Permissions The permissions of the command
     * @param description The description of the command
     * @param usage       The usage of that command
     */
    public JavaCordHandler(String[] aliases, CommandType type, Permissions permissions, String description, String usage) {
        super(aliases, type, permissions, description, usage);
    }


    public static class CommandInvocation extends GeneralInvocation {

        private Message message;

        public CommandInvocation(String[] args, Message message, String prefix) {
            super(args, prefix);
            this.message = message;
        }

        public Message getMessage(){
            return message;
        }
        public User getUser() { return message.getAuthor(); }
        public Channel getChannel() { return message.getChannelReceiver(); }
        public Server getServer() { return message.getChannelReceiver().getServer(); }
    }

    public abstract String run(CommandInvocation invocation);
}
