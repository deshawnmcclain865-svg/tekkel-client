package de.tekkel.client.hud;

import java.util.*;
import com.google.gson.JsonObject;
import de.tekkel.client.event.EventBus;

/**
 * Manages HUD elements and rendering
 */
public class HUDManager {
    
    private final List<HUDElement> hudElements = new ArrayList<>();
    private final EventBus eventBus;
    
    public HUDManager(EventBus eventBus) {
        this.eventBus = eventBus;
    }
    
    public void initializeHUDElements() {
        // HUD elements will be added here
    }
    
    public void addHUDElement(HUDElement element) {
        hudElements.add(element);
        eventBus.register(element);
    }
    
    public void removeHUDElement(HUDElement element) {
        hudElements.remove(element);
        eventBus.unregister(element);
    }
    
    public List<HUDElement> getHUDElements() {
        return new ArrayList<>(hudElements);
    }
    
    public JsonObject saveLayout() {
        JsonObject json = new JsonObject();
        for (HUDElement element : hudElements) {
            json.add(element.getName(), element.toJson());
        }
        return json;
    }
    
    public void loadLayout(JsonObject json) {
        for (HUDElement element : hudElements) {
            if (json.has(element.getName())) {
                element.fromJson(json.getAsJsonObject(element.getName()));
            }
        }
    }
}
