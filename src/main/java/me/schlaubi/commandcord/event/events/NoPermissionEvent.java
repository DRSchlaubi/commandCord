package me.schlaubi.commandcord.event.events;

import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.handlers.Command;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class NoPermissionEvent extends GeneralCommandEvent {

    public NoPermissionEvent(Command command, CommandEvent commandEvent) {
        super(command, commandEvent);
    }
}
