package me.schlaubi.commandcord.command.handlers.impl.jda;

import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.event.impl.JDACommandEvent;
import me.schlaubi.commandcord.command.handlers.Command;
import me.schlaubi.commandcord.command.permission.Permissions;
import me.schlaubi.commandcord.command.result.Result;
import me.schlaubi.commandcord.command.result.impl.JDAResult;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;

public abstract class JDACommand extends Command {

    /**
     * Create a command
     * @param aliases The aliases, which the command will listen to
     * @param commandType The type of the command
     * @param permissions The permissions of the command {@link me.schlaubi.commandcord.command.permission.Permissions}
     * @param description The description of the command (is needed for help message)
     * @param usage The usage of the command (is needed for help message)
     */
    public JDACommand(String[] aliases, CommandType commandType, Permissions permissions, String description, String usage) {
        super(aliases, commandType, permissions, description, usage);
    }

    @Override
    public Result run(String[] args, CommandEvent event) throws Exception {
        return run(args, (JDACommandEvent) event);
    }

    /**
     * Method that will be invoked when the command is invoked
     * @param args Arguments of the command
     * @param event The command event {@link me.schlaubi.commandcord.command.event.impl.JDACommandEvent}
     * @return Your Result Your Result {@link me.schlaubi.commandcord.command.result.impl.JDAResult}
     */
    public abstract Result run(String[] args, JDACommandEvent event) throws Exception;

    /**
     * Creates a Result from a Message created by a MessageBuilder {@link net.dv8tion.jda.core.MessageBuilder}
     * @param message The message built with the MessageBuilder
     * @return JDAResult
     */
    protected Result send(Message message) {
        return new JDAResult(message);
    }

    /**
     * Creates a Result from a String
     * @param message Content of the message
     * @return JDAResult
     */
    protected Result send(String message) {
        return new JDAResult(message);
    }

    /**
     * Creates a Result from a MessageEmbed built with a EmbedBuilder {@link net.dv8tion.jda.core.MessageBuilder}
     * @param embed The  MessageEmbed
     * @return JDAResult
     */
    protected Result send(MessageEmbed embed) {
        return new JDAResult(embed);
    }

    /**
     * Creates a Result from a EmbedBuilder {@link net.dv8tion.jda.core.MessageBuilder}
     * @param builder The EmbedBuilder
     * @return JDAResult
     */
    protected Result send(EmbedBuilder builder) {
        return new JDAResult(builder);
    }

}
