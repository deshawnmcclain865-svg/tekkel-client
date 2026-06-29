package de.tekkel.client.hud.impl;

import de.tekkel.client.hud.HUDElement;

/**
 * Coordinates Display HUD Element
 */
public class CoordinatesElement extends HUDElement {
    
    public CoordinatesElement() {
        super("Coordinates");
        this.x = 10;
        this.y = 40;
    }
    
    @Override
    public void render(float partialTicks) {
        if (!visible) return;
        // Rendering logic will be implemented here
    }
}
