package me.schlaubi.commandcord.event.events;

import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.handlers.Command;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class GeneralCommandEvent implements Event {

    private Command command;
    private CommandEvent commandEvent;

    public GeneralCommandEvent(Command command, CommandEvent commandEvent) {
        this.command = command;
        this.commandEvent = commandEvent;
    }

    public Command getCommand() {
        return command;
    }

    public CommandEvent getCommandEvent() {
        return commandEvent;
    }
}
