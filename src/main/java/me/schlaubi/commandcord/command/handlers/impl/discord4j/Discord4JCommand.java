package me.schlaubi.commandcord.command.handlers.impl.discord4j;

import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.event.impl.Discord4JCommandEvent;
import me.schlaubi.commandcord.command.handlers.Command;
import me.schlaubi.commandcord.command.permission.Permissions;
import me.schlaubi.commandcord.command.result.Result;
import me.schlaubi.commandcord.command.result.impl.Discord4JResult;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.util.EmbedBuilder;

/**
 * Use this class when you're using the Discord4J library
 */
public abstract class Discord4JCommand extends Command {

    /**
     * Create a command
     * @param aliases The aliases, which the command will listen to
     * @param commandType The type of the command
     * @param permissions The permissions of the command {@link me.schlaubi.commandcord.command.permission.Permissions}
     * @param description The description of the command (is needed for help message)
     * @param usage The usage of the command (is needed for help message)
     */
    public Discord4JCommand(String[] aliases, CommandType commandType, Permissions permissions, String description, String usage) {
        super(aliases, commandType, permissions, description, usage);
    }

    @Override
    public Result run(String[] args, CommandEvent event) {
        return run(args, ((Discord4JCommandEvent) event));
    }

    /**
     * Method that will be invoked when the command is invoked
     * @param args Arguments of the command
     * @param event The command event {@link me.schlaubi.commandcord.command.event.impl.Discord4JCommandEvent}
     * @return Your Result Your Result {@link me.schlaubi.commandcord.command.result.impl.Discord4JResult}
     */
    public abstract Result run(String[] args, Discord4JCommandEvent event);

    /**
     * Creates a Result from a String
     * @param message The content of the Message
     * @return Discord4JResult
     */
    protected Result send(String message) {
        return new Discord4JResult(message);
    }

    /**
     * Creates a Result from a EmbedObject created with a EmbedBuilder {@link sx.blah.discord.util.EmbedBuilder}
     * @param embed The Object built with the EmbedBuilder
     * @return Discord4JResult
     */
    protected Result send(EmbedObject embed) {
        return new Discord4JResult(embed);
    }

    /**
     * Creates a Result from a EmbedBuilder {@link sx.blah.discord.util.EmbedBuilder}
     * @param builder The EmbedBuilder
     * @return Discord4JResult
     */
    protected Result send(EmbedBuilder builder) {
        return new Discord4JResult(builder);
    }
}
