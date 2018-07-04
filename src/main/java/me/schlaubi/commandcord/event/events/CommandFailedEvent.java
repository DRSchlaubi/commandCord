package me.schlaubi.commandcord.event.events;

import me.schlaubi.commandcord.command.handlers.GeneralCommandHandler;
import me.schlaubi.commandcord.command.handlers.GeneralInvocation;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class CommandFailedEvent extends GeneralCommandEvent{

    private Throwable exception;

    public CommandFailedEvent(GeneralInvocation invocation, GeneralCommandHandler handler, Throwable exception) {
        super(invocation, handler);
        this.exception = exception;
    }

    public Throwable getException() {
        return exception;
    }
}
