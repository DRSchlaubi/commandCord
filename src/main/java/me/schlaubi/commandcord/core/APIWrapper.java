package me.schlaubi.commandcord.core;

public enum APIWrapper {

    JDA ("JDA"),
    JAVACORD ("Javacord"),
    DISCORD4J ("Discord4J");

    public String name;

    APIWrapper(String name){
        this.name = name;
    }
}
