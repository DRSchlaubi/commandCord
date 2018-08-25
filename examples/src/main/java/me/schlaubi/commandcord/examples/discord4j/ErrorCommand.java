package me.schlaubi.commandcord.examples.discord4j;

import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.handlers.Command;
import me.schlaubi.commandcord.command.permission.Permissions;
import me.schlaubi.commandcord.command.result.Result;

public class ErrorCommand extends Command {

    public ErrorCommand() {
        super(new String[] {"error"}, CommandType.GENERAL, Permissions.authorOnly(), "ERROR!!", "");
    }

    @Override
    public Result run(String[] strings, CommandEvent commandEvent) {
        Integer.parseInt("dasdads");
        return null;
    }
}
