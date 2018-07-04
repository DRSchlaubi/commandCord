package me.schlaubi.commandcord.examples.javacord;

import de.btobastian.javacord.entities.message.embed.EmbedBuilder;
import me.schlaubi.commandcord.command.handlers.JDACommandHandler;
import me.schlaubi.commandcord.command.handlers.JavaCordHandler;
import me.schlaubi.commandcord.event.EventAdapter;
import me.schlaubi.commandcord.event.events.CommandExecutedEvent;
import me.schlaubi.commandcord.event.events.CommandFailedEvent;
import me.schlaubi.commandcord.event.events.NoPermissionEvent;

import java.awt.*;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class EventListener extends EventAdapter {

    /* Command logging */
    @Override
    public void onCommandExecution(CommandExecutedEvent event) {
        JDACommandHandler.CommandInvocation invocation = (JDACommandHandler.CommandInvocation) event.getInvocation();
        System.out.println("Command " + event.getHandler().getAliases()[0] + " got executed by " + invocation.getMember().getAsMention());
    }

    /* Exception handling */
    @Override
    public void onCommandFail(CommandFailedEvent event) {
        JavaCordHandler.CommandInvocation invocation = (JavaCordHandler.CommandInvocation) event.getInvocation();
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.red)
                .setTitle("An unknown error occurred while parsing command!")
                .setDescription("" +
                        "`" + event.getException().getClass().getCanonicalName() + ": " + event.getException().getMessage() + "`");

        invocation.getChannel().sendMessage("", builder);
        event.getException().printStackTrace();
    }

    /* Permission handling */
    @Override
    public void onPermissionViolation(NoPermissionEvent event) {
        JavaCordHandler.CommandInvocation invocation = (JavaCordHandler.CommandInvocation) event.getInvocation();
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.red)
                .setTitle("No permission!")
                .setDescription("" +
                        "You don't have enough permissions to run this command");

        invocation.getChannel().sendMessage("", builder);
    }





}
