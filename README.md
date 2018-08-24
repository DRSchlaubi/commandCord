# commandCord
A very feature rich framework for discord bot commands written in Java
Getting started: [https://github.com/DRSchlaubi/commandCord/wiki/Create-your-first-command](https://github.com/DRSchlaubi/commandCord/wiki/Create-your-first-command)
## Implementation
You can import the library you found on the releases page via Build Path ([IntelliJ](https://stackoverflow.com/questions/34832059/how-to-add-a-project-to-build-path-in-intellij-idea), [Eclipse](https://wiki.eclipse.org/FAQ_How_do_I_add_an_extra_library_to_my_project%27s_classpath%3F)) <br>
[![Maven Central](https://img.shields.io/maven-central/v/me.schlaubi/commandCord.svg?label=Maven%20Central)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22me.schlaubi%22%20a%3A%22commandCord%22)<br>
Maven
```XML
<dependency>
  <groupId>me.schlaubi</groupId>
  <artifactId>commandCord</artifactId>
  <version>Version code from badge above</version>
</dependency>      
```
Gradle:
```JSON
compile 'me.schlaubi:commandCord:Version code from badge above'
```


# Supported libraries
* [JDA](https://github.com/DV8FromTheWorld/JDA)
* [Discord4J](https://github.com/Discord4J/Discord4J)
* [JavaCord](https://github.com/BtoBastian/Javacord)

# Features
* Guild specific prefixes
* Sub commands
* Typing!!!
* Permission system
* More aliases per command
* Blacklist support
* Events

# Usage
```Java
/* Build command manager */
 //Create manager
        CommandManager manager = new CommandManagerBuilder(APIWrapper.JDA)
                .enableGuildPrefixes(true)
                .setDefaultPrefix("!")
                .setApi(jdaInstance)
                //use test as guild specific prefix
                .setPrefixProvider(s -> "test")
                .enableBlacklist(true)
                //Randomly block one channel
                .setBlacklistProvider(channelId -> channelId.equals("470167577901924352"))
                .enableTyping(true)
                .setPermissionProvider(new PermissionProvider() {
                    @Override
                    public boolean isGuildOwner(Member member) {
                        return jda.getGuildById(member.getGuildId()).getOwner().getUser().getId().equals(member.getUserId());
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
        //Register commands
        manager.registerCommands(
                new JDAHelpCommand(),
                new ErrorCommand(),
                new TestCommand()
        );
        //Register events
        manager.getEventManager().registerListener(new Listener());
```

# Examples
* [JDA Example](https://github.com/DRSchlaubi/commandCord/tree/master/examples/src/main/java/me/schlaubi/commandcord/examples/jda)
* [Javacord Example](https://github.com/DRSchlaubi/commandCord/tree/master/examples/src/main/java/me/schlaubi/commandcord/examples/javacord)
 
  
