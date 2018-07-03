package me.schlaubi.commandcord.listeners.jda;

import net.dv8tion.jda.core.events.message.MessageUpdateEvent;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class JDAEditsListener extends JDAListener {

    @Override
    public void onMessageUpdate(MessageUpdateEvent event) {
        parseCommand(event.getMessage());
    }
}
