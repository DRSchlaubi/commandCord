package me.schlaubi.commandcord.event.events;

import me.schlaubi.commandcord.command.handlers.GeneralCommandHandler;
import me.schlaubi.commandcord.command.handlers.GeneralInvocation;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class CommandExecutedEvent extends GeneralCommandEvent {


    public CommandExecutedEvent(GeneralInvocation invocation, GeneralCommandHandler handler) {
        super(invocation, handler);
    }
}
