package de.tekkel.client.hud.impl;

import de.tekkel.client.hud.HUDElement;

/**
 * CPS Counter HUD Element
 */
public class CPSElement extends HUDElement {
    
    private int cps = 0;
    
    public CPSElement() {
        super("CPS");
        this.x = 10;
        this.y = 55;
    }
    
    @Override
    public void render(float partialTicks) {
        if (!visible) return;
        // Rendering logic will be implemented here
    }
    
    public void incrementCPS() { this.cps++; }
    public int getCPS() { return cps; }
}
