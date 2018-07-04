package me.schlaubi.commandcord.examples;

import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.handlers.JDACommandHandler;
import me.schlaubi.commandcord.command.permission.Permissions;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class PingCommand extends JDACommandHandler {

    public PingCommand() {
        super(new String[] {"ping"}, CommandType.FUN, Permissions.everyone(), "Funny test command", "ping");
    }

    @Override
    public Message run(CommandInvocation commandInvocation) {
        Integer.parseInt("dasasd");
        return new MessageBuilder().setContent("Ping: " + commandInvocation.getChannel().getJDA().getPing()).build();
    }
}
