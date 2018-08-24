package me.schlaubi.commandcord.examples.jda;

import me.schlaubi.commandcord.command.event.impl.JDACommandEvent;
import me.schlaubi.commandcord.event.EventAdapter;
import me.schlaubi.commandcord.event.events.CommandExecutedEvent;
import me.schlaubi.commandcord.event.events.CommandFailedEvent;
import me.schlaubi.commandcord.event.events.NoPermissionEvent;
import me.schlaubi.commandcord.examples.Utils;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;

public class Listener extends EventAdapter {

    /* Command logging */
    @Override
    public void onCommandExecution(CommandExecutedEvent event) {
        JDACommandEvent commandEvent = ((JDACommandEvent) event.getCommandEvent());
        System.out.println("[COMMAND] Command " + event.getCommand().getAliases()[0] + " got executed by " + commandEvent.getAuthor().getAsMention());
    }

    /* Exception handling */
    @Override
    public void onCommandFail(CommandFailedEvent event) {
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.red)
                .setTitle("An unknown error occurred while parsing command!")
                .setDescription("" +
                        "`" + Utils.excpetionToString(event.getThrowable())+ "`");

        ((JDACommandEvent) event.getCommandEvent()).getTextChannel().sendMessage(builder.build()).queue();
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

        ((JDACommandEvent) event.getCommandEvent()).getTextChannel().sendMessage(builder.build()).queue();
    }
}
