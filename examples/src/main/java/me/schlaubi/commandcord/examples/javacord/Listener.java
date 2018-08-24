package me.schlaubi.commandcord.examples.javacord;

import me.schlaubi.commandcord.command.event.impl.JavacordCommandEvent;
import me.schlaubi.commandcord.event.EventAdapter;
import me.schlaubi.commandcord.event.events.CommandExecutedEvent;
import me.schlaubi.commandcord.event.events.CommandFailedEvent;
import me.schlaubi.commandcord.event.events.NoPermissionEvent;
import me.schlaubi.commandcord.examples.Utils;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;

public class Listener extends EventAdapter {

    /* Command logging */
    @Override
    public void onCommandExecution(CommandExecutedEvent event) {
        JavacordCommandEvent commandEvent = ((JavacordCommandEvent) event.getCommandEvent());
        System.out.println("[COMMAND] Command " + event.getCommand().getAliases()[0] + " got executed by " + commandEvent.getAuthor().getMentionTag());
    }

    /* Exception handling */
    @Override
    public void onCommandFail(CommandFailedEvent event) {
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.red)
                .setTitle("An unknown error occurred while parsing command!")
                .setDescription("" +
                        "`" + Utils.excpetionToString(event.getThrowable())+ "`");

        ((JavacordCommandEvent) event.getCommandEvent()).getChannel().sendMessage(builder);
        event.getThrowable().printStackTrace();
    }

    /* Permission handling */
    @Override
    public void onPermissionViolation(NoPermissionEvent event) {
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.red)
                .setTitle("No permission!")
                .setDescription("" +
                        "You don't have enough permissions to run this command");

        ((JavacordCommandEvent) event.getCommandEvent()).getChannel().sendMessage(builder);
    }
}
