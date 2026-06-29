package de.tekkel.client.event.impl;

import de.tekkel.client.event.Event;

/**
 * Fired every frame for rendering
 */
public class RenderEvent extends Event {
    
    private final float partialTicks;
    
    public RenderEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }
    
    public float getPartialTicks() { return partialTicks; }
}
