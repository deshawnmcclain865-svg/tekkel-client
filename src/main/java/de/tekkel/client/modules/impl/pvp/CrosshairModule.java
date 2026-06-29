package de.tekkel.client.modules.impl.pvp;

import de.tekkel.client.modules.Module;
import de.tekkel.client.modules.ModuleCategory;

public class CrosshairModule extends Module {
    private float size = 2.0f;
    private int color = 0x3B82F6;
    private float thickness = 1.0f;
    private float length = 10.0f;
    private boolean rainbow = false;
    
    public CrosshairModule() {
        super("Crosshair", "Customize your crosshair", ModuleCategory.PVP);
    }
    
    public float getSize() { return size; }
    public void setSize(float size) { this.size = size; }
    public int getColor() { return color; }
    public void setColor(int color) { this.color = color; }
    public float getThickness() { return thickness; }
    public void setThickness(float thickness) { this.thickness = thickness; }
    public float getLength() { return length; }
    public void setLength(float length) { this.length = length; }
    public boolean isRainbow() { return rainbow; }
    public void setRainbow(boolean rainbow) { this.rainbow = rainbow; }
}
