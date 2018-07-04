package me.schlaubi.commandcord.examples.discord4j;

import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.core.APIWrapper;
import me.schlaubi.commandcord.core.CommandManager;
import me.schlaubi.commandcord.core.CommandManagerBuilder;
import me.schlaubi.commandcord.examples.providers.BlackListProvider;
import me.schlaubi.commandcord.examples.providers.PermissionProvider;
import me.schlaubi.commandcord.listeners.discord4j.Discord4Jlistener;
import me.schlaubi.commandcord.util.helpcommands.Discord4JHelpCommand;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class Main {

    public static IDiscordClient client;

    public static void main(String[] args){

        /* Build Discord4J */
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken("MjczNDk5OTUzNDU0Nzc2MzIw.Dh1jzg.em4md47r4H9SEgvIEPTF65On_qA")
                .registerListener(new Discord4Jlistener());
        client = clientBuilder.build();
        /* Login */
        client.login();

        /* Build command manager */
        CommandManager manager = new CommandManagerBuilder(APIWrapper.DISCORD4J)
                .enableGuildPrefixes(false)
                .setDefaultPrefix("!")
                .setPermissionProvider(new PermissionProvider())
                .enableBlacklist(true)
                .setBlacklistProvider(new BlackListProvider())
                .deleteCommandMessages(5)
                .setApi(client).build();
        manager.registerCommands(new Discord4JHelpCommand(new String[] {"help"}, CommandType.GENERAL, "Displays all commands", "help [command]"),
                new PingCommand(),
                new FailCommand());
        manager.getEventManager().registerListener(new EventListener());
    }
}
