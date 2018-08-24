package me.schlaubi.commandcord.examples.jda;

import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.event.impl.JDACommandEvent;
import me.schlaubi.commandcord.command.handlers.impl.jda.JDACommand;
import me.schlaubi.commandcord.command.handlers.impl.jda.JDASubCommand;
import me.schlaubi.commandcord.command.permission.Permissions;
import me.schlaubi.commandcord.command.result.Result;

public class TestCommand extends JDACommand {

    public TestCommand() {
        super(new String[] {"ping"}, CommandType.GENERAL, Permissions.everyone(), "PONG!", "[]");
        registerSubCommand(new SubCommand());
    }

    @Override
    public Result run(String[] strings, JDACommandEvent jdaCommandEvent) {
        return send(String.valueOf(jdaCommandEvent.getMessage().getJDA().getPing()));
    }

    private class SubCommand extends JDASubCommand {

        public SubCommand() {
            super(new String[] {"test"}, Permissions.everyone(), "USeLESS", "SUB COMMAND USAGE");
        }

        @Override
        public Result run(String[] strings, JDACommandEvent jdaCommandEvent) {
            return send("SUB COOMANNDDDD");
        }
    }
}
