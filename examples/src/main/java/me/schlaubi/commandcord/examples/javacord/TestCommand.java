package me.schlaubi.commandcord.examples.javacord;

import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.event.impl.JDACommandEvent;
import me.schlaubi.commandcord.command.event.impl.JavacordCommandEvent;
import me.schlaubi.commandcord.command.handlers.Command;
import me.schlaubi.commandcord.command.handlers.impl.jda.JDASubCommand;
import me.schlaubi.commandcord.command.permission.Permissions;
import me.schlaubi.commandcord.command.result.Result;
import me.schlaubi.commandcord.command.result.impl.JavacordResult;

public class TestCommand extends Command {
    public TestCommand() {
        super(new String[] {"ping"}, CommandType.GENERAL, Permissions.everyone(), "PONG!", "[]");
        registerSubCommand(new SubCommand());
    }

    @Override
    public Result run(String[] strings, CommandEvent commandEvent) {
        //There is no getPing() ;(
        return new JavacordResult(String.valueOf(((JavacordCommandEvent) commandEvent).getAuthor().getApi().getAccountType()));
    }

    private class SubCommand extends me.schlaubi.commandcord.command.handlers.SubCommand {

        public SubCommand() {
            super(new String[] {"test"}, Permissions.everyone(), "USeLESS", "SUB COMMAND USAGE");
        }

        @Override
        public Result run(String[] strings, CommandEvent commandEvent) {
            return new JavacordResult("SUB COOMANNDDDD");
        }
    }
}
