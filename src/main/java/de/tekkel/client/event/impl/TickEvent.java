package de.tekkel.client.event.impl;

import de.tekkel.client.event.Event;

/**
 * Fired every game tick
 */
public class TickEvent extends Event {
    
    private final float partialTicks;
    
    public TickEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }
    
    public float getPartialTicks() { return partialTicks; }
}
