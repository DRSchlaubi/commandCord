package me.schlaubi.commandcord.examples.javacord;

import com.google.common.util.concurrent.FutureCallback;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.core.APIWrapper;
import me.schlaubi.commandcord.core.CommandManager;
import me.schlaubi.commandcord.core.CommandManagerBuilder;
import me.schlaubi.commandcord.examples.providers.BlackListProvider;
import me.schlaubi.commandcord.examples.providers.PermissionProvider;
import me.schlaubi.commandcord.listeners.javacord.JavaCordListener;
import me.schlaubi.commandcord.util.helpcommands.JavaCordHelpCommand;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class Main {

    public static DiscordAPI api;

    public static void main(String[] args){
        // See "How to get the token" below
        api = Javacord.getApi("MjczNDk5OTUzNDU0Nzc2MzIw.Dh1jzg.em4md47r4H9SEgvIEPTF65On_qA", true);
        api.connect(new FutureCallback<DiscordAPI>() {
            @Override
            public void onSuccess(DiscordAPI api) {
                // register listener
                api.registerListener(new JavaCordListener());
                //Create command manager
                CommandManager manager = new CommandManagerBuilder(APIWrapper.JAVACORD)
                        .setDefaultPrefix("!")
                        .setPermissionProvider(new PermissionProvider())
                        .enableBlacklist(true)
                        .setBlacklistProvider(new BlackListProvider())
                        .setApi(api).build();
                /* Register commands */
                manager.registerCommands(
                        new JavaCordHelpCommand(new String[] {"help"}, CommandType.GENERAL, "Displays a list of commands", "help [command"),
                        new PingCommand(),
                        new FailCommand()
                );

                /* Register events*/
                manager.getEventManager().registerListener(new EventListener());
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
