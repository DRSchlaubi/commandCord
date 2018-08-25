package me.schlaubi.commandcord.examples.discord4j;

import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.event.impl.Discord4JCommandEvent;
import me.schlaubi.commandcord.command.handlers.Command;
import me.schlaubi.commandcord.command.permission.Permissions;
import me.schlaubi.commandcord.command.result.Result;
import me.schlaubi.commandcord.command.result.impl.Discord4JResult;

public class TestCommand extends Command {

    public TestCommand() {
        super(new String[] {"ping"}, CommandType.GENERAL, Permissions.everyone(), "PONG!", "[]");
        registerSubCommand(new SubCommand());
    }

    @Override
    public Result run(String[] strings, CommandEvent commandEvent) {
        //There is no getPing() ;(
        return new Discord4JResult(String.valueOf(((Discord4JCommandEvent) commandEvent).getAuthor().getClient().getOurUser()));
    }

    private class SubCommand extends me.schlaubi.commandcord.command.handlers.SubCommand {

        public SubCommand() {
            super(new String[] {"test"}, Permissions.everyone(), "USeLESS", "SUB COMMAND USAGE");
        }

        @Override
        public Result run(String[] strings, CommandEvent commandEvent) {
            return new Discord4JResult("SUB COOMANNDDDD");
        }
    }
}
