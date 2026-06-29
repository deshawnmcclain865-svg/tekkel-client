package de.tekkel.client.modules;

import com.google.gson.JsonObject;
import de.tekkel.client.event.EventBus;

/**
 * Base class for all modules
 */
public abstract class Module {
    
    protected String name;
    protected String description;
    protected ModuleCategory category;
    protected boolean enabled = false;
    protected int hotkey = -1;
    
    private EventBus eventBus;
    
    public Module(String name, ModuleCategory category) {
        this.name = name;
        this.category = category;
        this.description = "No description";
    }
    
    public Module(String name, String description, ModuleCategory category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }
    
    public void onEnable() {
        if (eventBus != null) {
            eventBus.register(this);
        }
    }
    
    public void onDisable() {
        if (eventBus != null) {
            eventBus.unregister(this);
        }
    }
    
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("enabled", enabled);
        json.addProperty("hotkey", hotkey);
        return json;
    }
    
    public void fromJson(JsonObject json) {
        if (json.has("enabled")) {
            setEnabled(json.get("enabled").getAsBoolean());
        }
        if (json.has("hotkey")) {
            hotkey = json.get("hotkey").getAsInt();
        }
    }
    
    public String getName() { return name; }
    public String getDescription() { return description; }
    public ModuleCategory getCategory() { return category; }
    public boolean isEnabled() { return enabled; }
    
    public void setEnabled(boolean enabled) {
        if (this.enabled == enabled) return;
        this.enabled = enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }
    
    public int getHotkey() { return hotkey; }
    public void setHotkey(int hotkey) { this.hotkey = hotkey; }
    public void setEventBus(EventBus eventBus) { this.eventBus = eventBus; }
}
