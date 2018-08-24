package me.schlaubi.commandcord.event.events;

import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.handlers.Command;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class CommandFailedEvent extends GeneralCommandEvent {

    private Throwable throwable;

    public CommandFailedEvent(Command command, CommandEvent commandEvent, Throwable throwable) {
        super(command, commandEvent);
        this.throwable = throwable;
    }


    public Throwable getThrowable() {
        return throwable;
    }
}
