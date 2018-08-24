package me.schlaubi.commandcord.examples.jda;

import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.event.impl.JDACommandEvent;
import me.schlaubi.commandcord.command.handlers.impl.jda.JDACommand;
import me.schlaubi.commandcord.command.permission.Permissions;
import me.schlaubi.commandcord.command.result.Result;

public class ErrorCommand extends JDACommand {

    public ErrorCommand() {
        super(new String[] {"error"}, CommandType.GENERAL, Permissions.authorOnly(), "PONG!", "");
    }

    @Override
    public Result run(String[] strings, JDACommandEvent jdaCommandEvent) {
        //Parse string as int to produce a excpetion
        Integer.parseInt("dsasd");
        return send("");
    }
}
