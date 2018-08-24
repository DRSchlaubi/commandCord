package me.schlaubi.commandcord.core.parser;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.event.impl.JavacordCommandEvent;
import me.schlaubi.commandcord.command.result.Result;
import me.schlaubi.commandcord.core.CommandParser;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.server.Server;

import java.util.concurrent.ExecutionException;

public class JavacordParser extends CommandParser {

    @Override
    protected CommandEvent parseEvent(String message, String guildId, String textChannelId, String messageId) {
        Message invokeMessage = null;
        try {
            invokeMessage = getChannelById(guildId, textChannelId).getMessageById(messageId).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return new JavacordCommandEvent(invokeMessage, invokeMessage.getServerTextChannel(), invokeMessage.getServer(), invokeMessage.getAuthor());
    }

    @Override
    protected void deleteInvokeMessage(String messageId, String guildId, String textChannelId) {
        try {
            getChannelById(guildId, textChannelId).getMessageById(messageId).get().delete();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void sendMessage(Result result, String guildId, String textChannelId) throws Exception {
        Server server = getGuildById(guildId);
        result.sendMessage(server.getTextChannelById(textChannelId).get(), server);
    }

    @Override
    protected void sendTyping(String guildId, String textChannelId) {
        getChannelById(guildId, textChannelId).type();
    }

    private TextChannel getChannelById(String guildId, String channelId) {
        return getGuildById(guildId).getTextChannelById(channelId).get();
    }

    private Server getGuildById(String guildId) {
        DiscordApi api = (DiscordApi) CommandCord.getInstance().getApi();
        return api.getServerById(guildId).get();
    }
}
