package me.schlaubi.commandcord.listeners.javacord;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageEditListener;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class JavaCordEditsListener extends JavaCordListener implements MessageEditListener {

    @Override
    public void onMessageEdit(DiscordAPI discordAPI, Message message, String s) {
        parseMessage(message);
    }
}
