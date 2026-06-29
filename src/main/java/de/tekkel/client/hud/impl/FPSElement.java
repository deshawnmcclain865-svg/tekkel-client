package de.tekkel.client.hud.impl;

import de.tekkel.client.hud.HUDElement;

/**
 * FPS Display HUD Element
 */
public class FPSElement extends HUDElement {
    
    public FPSElement() {
        super("FPS");
        this.x = 10;
        this.y = 10;
    }
    
    @Override
    public void render(float partialTicks) {
        if (!visible) return;
        // Rendering logic will be implemented here
    }
}
