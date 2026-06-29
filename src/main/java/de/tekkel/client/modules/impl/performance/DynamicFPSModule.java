package de.tekkel.client.modules.impl.performance;

import de.tekkel.client.modules.Module;
import de.tekkel.client.modules.ModuleCategory;

public class DynamicFPSModule extends Module {
    public DynamicFPSModule() {
        super("Dynamic FPS", "Adjust FPS based on activity", ModuleCategory.PERFORMANCE);
    }
}
