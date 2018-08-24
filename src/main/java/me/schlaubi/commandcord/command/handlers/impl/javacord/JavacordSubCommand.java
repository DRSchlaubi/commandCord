package me.schlaubi.commandcord.command.handlers.impl.javacord;

import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.event.impl.JavacordCommandEvent;
import me.schlaubi.commandcord.command.handlers.SubCommand;
import me.schlaubi.commandcord.command.permission.Permissions;
import me.schlaubi.commandcord.command.result.Result;
import me.schlaubi.commandcord.command.result.impl.JavacordResult;

public abstract class JavacordSubCommand extends SubCommand {
    public JavacordSubCommand(String[] aliases, Permissions permissions, String description, String usage) {
        super(aliases, permissions, description, usage);
    }

    @Override
    public Result run(String[] args, CommandEvent event) {
        return run(args, (JavacordCommandEvent) event);
    }

    public abstract Result run(String[] args, JavacordCommandEvent event);

    protected Result send(String message) {
        return new JavacordResult(message);
    }

    protected Result send(org.javacord.api.entity.message.embed.EmbedBuilder builder) {
        return new JavacordResult(builder);
    }
}
