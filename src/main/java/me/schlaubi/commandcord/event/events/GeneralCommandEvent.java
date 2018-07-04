package me.schlaubi.commandcord.event.events;

import me.schlaubi.commandcord.command.handlers.GeneralCommandHandler;
import me.schlaubi.commandcord.command.handlers.GeneralInvocation;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class GeneralCommandEvent implements Event{
    private GeneralInvocation invocation;
    private GeneralCommandHandler handler;

    public GeneralCommandEvent(GeneralInvocation invocation, GeneralCommandHandler handler) {
        this.invocation = invocation;
        this.handler = handler;
    }

    public GeneralInvocation getInvocation() {
        return invocation;
    }

    public GeneralCommandHandler getHandler() {
        return handler;
    }
}
