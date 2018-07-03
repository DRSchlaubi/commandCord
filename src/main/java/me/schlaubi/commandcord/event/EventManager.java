package me.schlaubi.commandcord.event;

import me.schlaubi.commandcord.event.events.Event;

import java.util.ArrayList;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class EventManager {

    public ArrayList<Listener> listeners = new ArrayList<>();

    public void registerListener(Listener listener){
        if(!(listener instanceof EventAdapter))
            throw new IllegalArgumentException("Listener needs to extend EventAdapter");
        if(!listeners.contains(listener))
            listeners.add(listener);
    }

    public void unregister(Listener listener){
        if(!(listener instanceof EventAdapter))
            throw new IllegalArgumentException("Listener needs to extend EventAdapter");
        listeners.remove(listener);
    }

    public void call(Event event){
        listeners.forEach(l -> {
            try {
                ((EventAdapter) l).onEvent(event);
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
