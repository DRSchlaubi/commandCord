package me.schlaubi.commandcord.command.handlers.impl.javacord;

import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.event.impl.JavacordCommandEvent;
import me.schlaubi.commandcord.command.handlers.SubCommand;
import me.schlaubi.commandcord.command.permission.Permissions;
import me.schlaubi.commandcord.command.result.Result;
import me.schlaubi.commandcord.command.result.impl.JavacordResult;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public abstract class JavacordSubCommand extends SubCommand {
    public JavacordSubCommand(String[] aliases, Permissions permissions, String description, String usage) {
        super(aliases, permissions, description, usage);
    }

    @Override
    public Result run(String[] args, CommandEvent event) {
        return run(args, (JavacordCommandEvent) event);
    }

    /**
     * Method that will be invoked when the command is invoked
     * @param args Arguments of the command
     * @param event The command event {@link me.schlaubi.commandcord.command.event.impl.JavacordCommandEvent}
     * @return Your Result Your Result {@link me.schlaubi.commandcord.command.result.impl.JavacordResult}
     */
    public abstract Result run(String[] args, JavacordCommandEvent event);

    /**
     * Creates a Result from a String
     * @param message Content of the message
     * @return JavacordResult
     */
    protected Result send(String message) {
        return new JavacordResult(message);
    }

    /**
     * Creates a Result from a EmbedBuilder {@link org.javacord.api.entity.message.embed.EmbedBuilder}
     * @param builder The EmbedBuilder
     * @return JavacordResult
     */
    protected Result send(EmbedBuilder builder) {
        return new JavacordResult(builder);
    }
}
