package me.schlaubi.commandcord.command.result.impl;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.result.Result;
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

public class Discord4JResult implements Result {

    private EmbedObject embedObject;
    private String message;
    protected IMessage sentMessage = null;

    @Override
    public void sendMessage(Object textChannel, Object guild) {
        Channel channel = (Channel) textChannel;
        if (PermissionUtils.hasPermissions((IGuild) guild, ((IDiscordClient) CommandCord.getInstance().getApi()).getOurUser(), Permissions.SEND_MESSAGES))
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
    }

    public Discord4JResult(String message) {
        this.message = message;
    }

    public Discord4JResult(EmbedObject embedObject) {
        this.embedObject = embedObject;
    }

    public Discord4JResult(EmbedBuilder embedBuilder) {
        this(embedBuilder.build());
    }
}
