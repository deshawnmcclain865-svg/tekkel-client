package de.tekkel.client.modules.impl.comfort;

import de.tekkel.client.modules.Module;
import de.tekkel.client.modules.ModuleCategory;

public class ZoomModule extends Module {
    private float zoomLevel = 3.0f;
    
    public ZoomModule() {
        super("Zoom", "Zoom in on targets", ModuleCategory.COMFORT);
    }
    
    public float getZoomLevel() { return zoomLevel; }
    public void setZoomLevel(float level) { this.zoomLevel = level; }
}
