# commandCord
A very feature rich framework for discord bot commands written in Java
## Implementation
You can import the library you found on the releases page via Build Path ([IntelliJ](https://stackoverflow.com/questions/34832059/how-to-add-a-project-to-build-path-in-intellij-idea), [Eclipse](https://wiki.eclipse.org/FAQ_How_do_I_add_an_extra_library_to_my_project%27s_classpath%3F))
[![Maven Central](https://img.shields.io/maven-central/v/me.schlaubi/commandCord.svg?label=Maven%20Central)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22me.schlaubi%22%20a%3A%22commandCord%22)
Maven
```XML
<dependency>
  <groupId>me.schlaubi</groupId>
  <artifactId>commandCord</artifactId>
  <version>1.0.0</version>
</dependency>      
```
Gradle:
```JSON
compile 'me.schlaubi:commandCord:1.0.0'
```


# Supported libraries
* [JDA](https://github.com/DV8FromTheWorld/JDA)
* [Discord4J](https://github.com/Discord4J/Discord4J)
* [JavaCord](https://github.com/BtoBastian/Javacord)

# Features
* Guild specific prefixes
* Permission system
* More aliases per command
* Blacklist support
* Events

# Usage
```Java
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
```

# Examples
* Information providers
  * [PermissionProvider](https://github.com/DRSchlaubi/commandCord/blob/master/commandcord.examples/src/main/java/me/schlaubi/commandcord/examples/providers/PermissionProvider.java)
  * [PrefixProvider](https://github.com/DRSchlaubi/commandCord/blob/master/commandcord.examples/src/main/java/me/schlaubi/commandcord/examples/providers/PrefixProvider.java)
  * [BlacklistProvider](https://github.com/DRSchlaubi/commandCord/blob/master/commandcord.examples/src/main/java/me/schlaubi/commandcord/examples/providers/BlackListProvider.java)
* Command handlers
  * [JDAHandler](https://github.com/DRSchlaubi/commandCord/blob/master/commandcord.examples/src/main/java/me/schlaubi/commandcord/examples/jda/PingCommand.java)
  * [Discord4JHandler](https://github.com/DRSchlaubi/commandCord/blob/master/commandcord.examples/src/main/java/me/schlaubi/commandcord/examples/discord4j/PingCommand.java)
  * [JavaCordHandler](https://github.com/DRSchlaubi/commandCord/blob/master/commandcord.examples/src/main/java/me/schlaubi/commandcord/examples/javacord/PingCommand.java)
  
