package me.schlaubi.commandcord.listeners.javacord;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class JavaCordListener implements MessageCreateListener {

    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        parseMessage(message);
    }

    void parseMessage(Message message){

    }
}
