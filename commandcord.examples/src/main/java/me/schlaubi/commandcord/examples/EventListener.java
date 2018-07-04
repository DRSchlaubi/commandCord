package me.schlaubi.commandcord.examples;

import me.schlaubi.commandcord.command.handlers.JDACommandHandler;
import me.schlaubi.commandcord.event.EventAdapter;
import me.schlaubi.commandcord.event.events.CommandExecutedEvent;
import me.schlaubi.commandcord.event.events.CommandFailedEvent;
import me.schlaubi.commandcord.event.events.NoPermissionEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class EventListener extends EventAdapter {

    @Override
    public void onCommandExecution(CommandExecutedEvent event) {
        JDACommandHandler.CommandInvocation invocation = (JDACommandHandler.CommandInvocation) event.getInvocation();
        System.out.println("Command " + event.getHandler().getAliases()[0] + " got executed by " + invocation.getMember().getAsMention());
    }

    @Override
    public void onCommandFail(CommandFailedEvent event) {
        JDACommandHandler.CommandInvocation invocation = (JDACommandHandler.CommandInvocation) event.getInvocation();
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.red)
                .setTitle("An unknown error occurred while parsing command!")
                .setDescription("" +
                        "`" + event.getException().getClass().getCanonicalName() + ": " + event.getException().getMessage() + "`");

        invocation.getChannel().sendMessage(builder.build()).queue();
        event.getException().printStackTrace();
    }

    @Override
    public void onPermissionViolation(NoPermissionEvent event) {
        JDACommandHandler.CommandInvocation invocation = (JDACommandHandler.CommandInvocation) event.getInvocation();
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.red)
                .setTitle("No permission!")
                .setDescription("" +
                        "You don't have enough permissions to run this command");

        invocation.getChannel().sendMessage(builder.build()).queue();
    }





}
