package me.schlaubi.commandcord.command.result.impl;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.result.Result;
import net.dv8tion.jda.core.Permission;
import org.apache.log4j.Logger;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.impl.obj.Channel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.PermissionUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Use this class when you're using the Discord4J library
 */
public class Discord4JResult implements Result {

    private final Logger logger = Logger.getLogger(Result.class);

    private EmbedObject embedObject;
    private String message;
    protected IMessage sentMessage = null;

    @Override
    public void sendMessage(Object textChannel, Object guild) {
        Channel channel = (Channel) textChannel;
        if (PermissionUtils.hasPermissions((IGuild) guild, ((IDiscordClient) CommandCord.getInstance().getApi()).getOurUser(), Permissions.SEND_MESSAGES)) {
            if (embedObject == null)
                sentMessage = channel.sendMessage(message);
            else
                sentMessage = channel.sendMessage(embedObject);
            if (CommandCord.getInstance().getDeleteCommandMessage() != 0)
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        sentMessage.delete();
                    }
                }, CommandCord.getInstance().getDeleteCommandMessage() * 1000);
        } else
            logger.warn(String.format("Warning: Invoke message could not be sent due to an permission error missing permission: %s Guild: %s", Permission.MESSAGE_WRITE, channel.getGuild().getStringID()));


    }

    /**
     * Creates a Result from a String
     * @param message The content of the Message
     */
    public Discord4JResult(String message) {
        this.message = message;
    }

    /**
     * Creates a Result from a EmbedObject created with a EmbedBuilder {@link sx.blah.discord.util.EmbedBuilder}
     * @param embedObject The Object built with the EmbedBuilder
     */
    public Discord4JResult(EmbedObject embedObject) {
        this.embedObject = embedObject;
    }

    /**
     * Creates a Result from a EmbedBuilder {@link sx.blah.discord.util.EmbedBuilder}
     * @param embedBuilder The EmbedBuilder
     */
    public Discord4JResult(EmbedBuilder embedBuilder) {
        this(embedBuilder.build());
    }
}
