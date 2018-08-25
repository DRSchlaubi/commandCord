package me.schlaubi.commandcord.examples.discord4j;

import me.schlaubi.commandcord.command.event.impl.Discord4JCommandEvent;
import me.schlaubi.commandcord.event.EventAdapter;
import me.schlaubi.commandcord.event.events.CommandExecutedEvent;
import me.schlaubi.commandcord.event.events.CommandFailedEvent;
import me.schlaubi.commandcord.event.events.NoPermissionEvent;
import me.schlaubi.commandcord.examples.Utils;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;

public class Listener extends EventAdapter {

    /* Command logging */
    @Override
    public void onCommandExecution(CommandExecutedEvent event) {
        Discord4JCommandEvent commandEvent = ((Discord4JCommandEvent) event.getCommandEvent());
        System.out.println("[COMMAND] Command " + event.getCommand().getAliases()[0] + " got executed by " + commandEvent.getAuthor().getName());
    }

    /* Exception handling */
    @Override
    public void onCommandFail(CommandFailedEvent event) {
        EmbedBuilder builder = new EmbedBuilder()
                .withColor(Color.red)
                .withTitle("An unknown error occurred while parsing command!")
                .withDesc("" +
                        "`" + Utils.excpetionToString(event.getThrowable())+ "`");

        ((Discord4JCommandEvent) event.getCommandEvent()).getChannel().sendMessage(builder.build());
        event.getThrowable().printStackTrace();
    }

    /* Permission handling */
    @Override
    public void onPermissionViolation(NoPermissionEvent event) {
        EmbedBuilder builder = new EmbedBuilder()
                .withColor(Color.red)
                .withTitle("No permission!")
                .withDesc("" +
                        "You don't have enough permissions to run this command");

        ((Discord4JCommandEvent) event.getCommandEvent()).getChannel().sendMessage(builder.build());
    }
}
