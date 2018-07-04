package me.schlaubi.commandcord.event.events;

import me.schlaubi.commandcord.command.handlers.GeneralCommandHandler;
import me.schlaubi.commandcord.command.handlers.GeneralInvocation;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class NoPermissionEvent extends GeneralCommandEvent {

    public NoPermissionEvent(GeneralInvocation invocation, GeneralCommandHandler handler) {
        super(invocation, handler);
    }
}
