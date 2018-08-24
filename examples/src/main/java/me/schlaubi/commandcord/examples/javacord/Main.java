package me.schlaubi.commandcord.examples.javacord;

import me.schlaubi.commandcord.command.permission.Member;
import me.schlaubi.commandcord.command.permission.PermissionProvider;
import me.schlaubi.commandcord.core.APIWrapper;
import me.schlaubi.commandcord.core.CommandManager;
import me.schlaubi.commandcord.core.CommandManagerBuilder;
import me.schlaubi.commandcord.examples.TOKEN;
import me.schlaubi.commandcord.listeners.javacord.JavaCordListener;
import me.schlaubi.commandcord.util.helpcommands.JavacordHelpCommand;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {

    public static void main(String[] args) {

        DiscordApi api = new DiscordApiBuilder().setToken(TOKEN.token).login().join();
        api.addMessageCreateListener(new JavaCordListener());
        CommandManager manager = new CommandManagerBuilder(APIWrapper.JDA)
                .enableGuildPrefixes(true)
                .setDefaultPrefix("!")
                .setApi(api)
                //use test as guild specific prefix
                .setPrefixProvider(s -> "test")
                .enableBlacklist(true)
                //Randomly block one channel
                .setBlacklistProvider(channelId -> channelId.equals("470167577901924352"))
                .enableTyping(true)
                .setPermissionProvider(new PermissionProvider() {
                    @Override
                    public boolean isGuildOwner(Member member) {
                        return api.getServerById(member.getGuildId()).get().getOwner().getIdAsString().equals(member.getUserId());
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
                new JavacordHelpCommand(),
                new ErrorCommand(),
                new TestCommand()
        );
        manager.getEventManager().registerListener(new Listener());
    }
}
