package me.schlaubi.commandcord.event;

import me.schlaubi.commandcord.event.events.CommandExecutedEvent;
import me.schlaubi.commandcord.event.events.CommandFailedEvent;
import me.schlaubi.commandcord.event.events.Event;
import me.schlaubi.commandcord.event.events.NoPermissionEvent;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public abstract class EventAdapter implements Listener {

    public void onCommandExecution(CommandExecutedEvent event) {
    }

    public void onCommandFail(CommandFailedEvent event) {
    }

    public void onPermissionViolation(NoPermissionEvent event) {
    }

    public void onEvent(Event event) {
        if (event instanceof CommandExecutedEvent)
            this.onCommandExecution((CommandExecutedEvent) event);
        else if (event instanceof CommandFailedEvent)
            this.onCommandFail((CommandFailedEvent) event);
        else if (event instanceof NoPermissionEvent)
            this.onPermissionViolation((NoPermissionEvent) event);
    }
}
