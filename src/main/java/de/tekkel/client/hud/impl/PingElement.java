package de.tekkel.client.hud.impl;

import de.tekkel.client.hud.HUDElement;

/**
 * Ping Display HUD Element
 */
public class PingElement extends HUDElement {
    
    public PingElement() {
        super("Ping");
        this.x = 10;
        this.y = 25;
    }
    
    @Override
    public void render(float partialTicks) {
        if (!visible) return;
        // Rendering logic will be implemented here
    }
}
