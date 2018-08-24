package me.schlaubi.commandcord.examples.javacord;

import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.handlers.Command;
import me.schlaubi.commandcord.command.permission.Permissions;
import me.schlaubi.commandcord.command.result.Result;
import me.schlaubi.commandcord.command.result.impl.JavacordResult;

public class ErrorCommand extends Command {

    public ErrorCommand() {
        super(new String[] {"error"}, CommandType.GENERAL, Permissions.authorOnly(), "hey", "no");
    }

    @Override
    public Result run(String[] strings, CommandEvent commandEvent) {
        Integer.parseInt("DASDADA");
        return new JavacordResult("hey");
    }
}
