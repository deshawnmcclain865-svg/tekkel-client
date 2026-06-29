package de.tekkel.client.hud.impl;

import de.tekkel.client.hud.HUDElement;

/**
 * Keystrokes Display HUD Element
 */
public class KeystrokesElement extends HUDElement {
    
    public KeystrokesElement() {
        super("Keystrokes");
    }
    
    @Override
    public void render(float partialTicks) {
        if (!visible) return;
        // Rendering logic will be implemented here
    }
}
