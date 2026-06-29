package de.tekkel.client.hud.timezone;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import com.google.gson.JsonObject;

/**
 * Represents a single timezone with formatted time display
 */
public class TimeZoneDisplay {
    
    private final String name;
    private final ZoneId zoneId;
    private final String label;
    private final DateTimeFormatter timeFormat;
    private final DateTimeFormatter dateFormat;
    private String displayTime = "--:--:--";
    private String displayDate = "---";
    
    public TimeZoneDisplay(String name, String zoneIdString, String label) {
        this.name = name;
        this.zoneId = ZoneId.of(zoneIdString);
        this.label = label;
        this.timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.dateFormat = DateTimeFormatter.ofPattern("MMM dd");
    }
    
    public void update() {
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        displayTime = now.format(timeFormat);
        displayDate = now.format(dateFormat);
    }
    
    public String getDisplayString() {
        return String.format("%s: %s (%s)", label, displayTime, displayDate);
    }
    
    public String getShortDisplay() {
        return String.format("%s: %s", label, displayTime);
    }
    
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("name", name);
        json.addProperty("zoneId", zoneId.getId());
        json.addProperty("label", label);
        json.addProperty("time", displayTime);
        json.addProperty("date", displayDate);
        return json;
    }
    
    public String getName() { return name; }
    public ZoneId getZoneId() { return zoneId; }
    public String getLabel() { return label; }
    public String getDisplayTime() { return displayTime; }
    public String getDisplayDate() { return displayDate; }
}
