package me.schlaubi.commandcord.examples.discord4j;

import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.handlers.Discord4JCommandHandler;
import me.schlaubi.commandcord.command.handlers.JDACommandHandler;
import me.schlaubi.commandcord.command.permission.Permissions;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class PingCommand extends Discord4JCommandHandler {

    /* Example command */

    public PingCommand() {
        super(new String[] {"ping"}, CommandType.FUN, Permissions.everyone(), "Funny test command", "ping");
    }

    @Override
    public String run(CommandInvocation commandInvocation) {
        return  "Ping: 1337";
    }
}
