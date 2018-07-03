package me.schlaubi.commandcord.event;

import me.schlaubi.commandcord.event.events.CommandExecutedEvent;
import me.schlaubi.commandcord.event.events.CommandFailedEvent;
import me.schlaubi.commandcord.event.events.Event;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public abstract class EventAdapter {

    public void onCommandExecution(CommandExecutedEvent event) {}

    public void onCommandFail(CommandFailedEvent event) {}

    public void onEvent(Event event){
        if(event instanceof CommandExecutedEvent)
            this.onCommandExecution((CommandExecutedEvent) event);
        else if (event instanceof CommandFailedEvent)
            this.onCommandFail((CommandFailedEvent) event);
    }
}
