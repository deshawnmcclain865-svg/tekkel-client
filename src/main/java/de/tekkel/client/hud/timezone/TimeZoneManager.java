package de.tekkel.client.hud.timezone;

import de.tekkel.client.TekkelClient;
import java.util.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

/**
 * Manages multiple timezones for display
 */
public class TimeZoneManager {
    
    private final List<TimeZoneDisplay> timeZones = new ArrayList<>();
    private long lastUpdate = 0;
    private static final long UPDATE_INTERVAL = 100; // Update every 100ms
    
    public TimeZoneManager() {
        initializeDefaultTimeZones();
    }
    
    private void initializeDefaultTimeZones() {
        // UTC
        addTimeZone("UTC", "UTC", "UTC");
        
        // America
        addTimeZone("Eastern", "America/New_York", "EST/EDT");
        addTimeZone("Central", "America/Chicago", "CST/CDT");
        addTimeZone("Mountain", "America/Denver", "MST/MDT");
        addTimeZone("Pacific", "America/Los_Angeles", "PST/PDT");
        
        // Europe
        addTimeZone("London", "Europe/London", "GMT/BST");
        addTimeZone("Berlin", "Europe/Berlin", "CET/CEST");
        addTimeZone("Paris", "Europe/Paris", "CET/CEST");
        addTimeZone("Moscow", "Europe/Moscow", "MSK");
        
        // Asia
        addTimeZone("Dubai", "Asia/Dubai", "GST");
        addTimeZone("Bangkok", "Asia/Bangkok", "ICT");
        addTimeZone("Hong Kong", "Asia/Hong_Kong", "HKT");
        addTimeZone("Tokyo", "Asia/Tokyo", "JST");
        addTimeZone("Sydney", "Australia/Sydney", "AEDT/AEST");
    }
    
    public void addTimeZone(String name, String zoneId, String label) {
        timeZones.add(new TimeZoneDisplay(name, zoneId, label));
    }
    
    public void removeTimeZone(String name) {
        timeZones.removeIf(tz -> tz.getName().equals(name));
    }
    
    public void update() {
        long now = System.currentTimeMillis();
        if (now - lastUpdate > UPDATE_INTERVAL) {
            for (TimeZoneDisplay tz : timeZones) {
                tz.update();
            }
            lastUpdate = now;
        }
    }
    
    public List<TimeZoneDisplay> getTimeZones() {
        return new ArrayList<>(timeZones);
    }
    
    public TimeZoneDisplay getTimeZone(String name) {
        return timeZones.stream()
            .filter(tz -> tz.getName().equals(name))
            .findFirst()
            .orElse(null);
    }
    
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        JsonArray array = new JsonArray();
        for (TimeZoneDisplay tz : timeZones) {
            array.add(tz.toJson());
        }
        json.add("timeZones", array);
        return json;
    }
    
    public int getTimeZoneCount() { return timeZones.size(); }
}
