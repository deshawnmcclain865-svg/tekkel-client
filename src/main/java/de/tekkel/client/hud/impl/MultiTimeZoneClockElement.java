package de.tekkel.client.hud.impl;

import de.tekkel.client.hud.HUDElement;
import de.tekkel.client.hud.timezone.TimeZoneManager;
import de.tekkel.client.hud.timezone.TimeZoneDisplay;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

/**
 * Multi-TimeZone Clock HUD Element
 */
public class MultiTimeZoneClockElement extends HUDElement {
    
    private final TimeZoneManager timeZoneManager;
    private long lastUpdate = 0;
    private static final long UPDATE_INTERVAL = 100; // Update every 100ms
    private boolean expandedView = false;
    
    public MultiTimeZoneClockElement() {
        super("Multi-Timezone Clock");
        this.timeZoneManager = new TimeZoneManager();
        this.x = 10;
        this.y = 85;
        this.scale = 1.0f;
    }
    
    @Override
    public void render(float partialTicks) {
        if (!visible) return;
        
        long now = System.currentTimeMillis();
        if (now - lastUpdate > UPDATE_INTERVAL) {
            timeZoneManager.update();
            lastUpdate = now;
        }
        
        // Rendering logic will be implemented with Minecraft render system
    }
    
    public void toggleExpandedView() {
        expandedView = !expandedView;
    }
    
    public TimeZoneManager getTimeZoneManager() {
        return timeZoneManager;
    }
    
    public String getDisplayString() {
        if (expandedView) {
            StringBuilder sb = new StringBuilder();
            for (TimeZoneDisplay tz : timeZoneManager.getTimeZones()) {
                sb.append(tz.getDisplayString()).append("\n");
            }
            return sb.toString();
        } else {
            // Show only first 3 timezones in compact mode
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (TimeZoneDisplay tz : timeZoneManager.getTimeZones()) {
                if (count >= 3) break;
                sb.append(tz.getShortDisplay()).append(" | ");
                count++;
            }
            return sb.toString();
        }
    }
    
    @Override
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        json.addProperty("expandedView", expandedView);
        json.add("timeZones", timeZoneManager.toJson().get("timeZones"));
        return json;
    }
    
    public boolean isExpandedView() { return expandedView; }
}
