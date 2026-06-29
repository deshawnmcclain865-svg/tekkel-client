package de.tekkel.client.modules;

/**
 * Enum for module categories
 */
public enum ModuleCategory {
    PVP("PvP"),
    COMFORT("Comfort"),
    PERFORMANCE("Performance"),
    COSMETIC("Cosmetic"),
    OTHER("Other");
    
    private final String displayName;
    
    ModuleCategory(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() { return displayName; }
}
