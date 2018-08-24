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

public abstract class Discord4JCommand extends Command {

    public Discord4JCommand(String[] aliases, CommandType commandType, Permissions permissions, String description, String usage) {
        super(aliases, commandType, permissions, description, usage);
    }

    @Override
    public Result run(String[] args, CommandEvent event) {
        return run(args, ((Discord4JCommandEvent) event));
    }

    public abstract Result run(String[] args, Discord4JCommandEvent event);

    protected Result send(String message) {
        return new Discord4JResult(message);
    }

    protected Result send(EmbedObject embed) {
        return new Discord4JResult(embed);
    }

    protected Result send(EmbedBuilder builder) {
        return new Discord4JResult(builder);
    }
}
