package me.schlaubi.commandcord.listeners.javacord;

import org.javacord.api.event.message.MessageEditEvent;
import org.javacord.api.listener.message.MessageEditListener;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class JavaCordEditsListener extends JavaCordListener implements MessageEditListener {

    @Override
    public void onMessageEdit(MessageEditEvent messageEditEvent) {
        parseMessage(messageEditEvent.getMessage().get());
    }
}
