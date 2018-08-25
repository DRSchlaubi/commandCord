package me.schlaubi.commandcord.examples.discord4j;

import me.schlaubi.commandcord.command.permission.Member;
import me.schlaubi.commandcord.command.permission.PermissionProvider;
import me.schlaubi.commandcord.core.APIWrapper;
import me.schlaubi.commandcord.core.CommandManager;
import me.schlaubi.commandcord.core.CommandManagerBuilder;
import me.schlaubi.commandcord.examples.TOKEN;
import me.schlaubi.commandcord.listeners.discord4j.Discord4JListener;
import me.schlaubi.commandcord.util.helpcommands.Discord4JHelpCommand;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

public class Main {

    public static void main(String[] args) {
        //Build Discord client
        IDiscordClient cli = new ClientBuilder().withToken(TOKEN.token).build();
        //Register listener
        cli.getDispatcher().registerListener(new Discord4JListener());
        //Login
        cli.login();

        //Build manager
        CommandManager manager = new CommandManagerBuilder(APIWrapper.DISCORD4J)
                .enableGuildPrefixes(true)
                .setDefaultPrefix("!")
                .setApi(cli)
                //use test as guild specific prefix
                .setPrefixProvider(s -> "test")
                .enableBlacklist(true)
                //Randomly block one channel
                .setBlacklistProvider(channelId -> channelId.equals("470167577901924352"))
                .enableTyping(true)
                .setPermissionProvider(new PermissionProvider() {
                    @Override
                    public boolean isGuildOwner(Member member) {
                        return cli.getGuildByID(Long.parseLong(member.getGuildId())).getOwner().getStringID().equals(member.getUserId());
                    }

                    @Override
                    public boolean isBotAuthor(Member member) {
                        return member.getUserId().equals("264048760580079616");
                    }

                    @Override
                    public boolean hasPermissionNode(Member member, String s) {
                        return false;
                    }

                    @Override
                    public boolean hasPermissionLevel(Member member, int i) {
                        return false;
                    }
                }).build();
        manager.registerCommands(
                new Discord4JHelpCommand(),
                new ErrorCommand(),
                new TestCommand()
        );
        manager.getEventManager().registerListener(new Listener());
    }
}
