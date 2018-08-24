package me.schlaubi.commandcord.command.handlers.impl.jda;

import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.event.impl.JDACommandEvent;
import me.schlaubi.commandcord.command.handlers.SubCommand;
import me.schlaubi.commandcord.command.permission.Permissions;
import me.schlaubi.commandcord.command.result.Result;
import me.schlaubi.commandcord.command.result.impl.JDAResult;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;

public abstract class JDASubCommand extends SubCommand {

    public JDASubCommand(String[] aliases, Permissions permissions, String description, String usage) {
        super(aliases, permissions, description, usage);
    }

    @Override
    public Result run(String[] args, CommandEvent event) {
        return run(args, (JDACommandEvent) event);
    }

    public abstract Result run(String[] args, JDACommandEvent event);

    protected Result send(Message message) {
        return new JDAResult(message);
    }

    protected Result send(String message) {
        return new JDAResult(message);
    }

    protected Result send(MessageEmbed embed) {
        return new JDAResult(embed);
    }

    protected Result send(EmbedBuilder builder) {
        return new JDAResult(builder);
    }

}
