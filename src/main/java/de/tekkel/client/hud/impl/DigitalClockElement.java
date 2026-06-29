package de.tekkel.client.hud.impl;

import de.tekkel.client.hud.HUDElement;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Digital Clock HUD Element displaying current time
 */
public class DigitalClockElement extends HUDElement {
    
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("EEE, MMM dd yyyy");
    
    private String currentTime = "00:00:00";
    private String currentDate = "Loading...";
    private long lastUpdate = 0;
    private static final long UPDATE_INTERVAL = 100; // Update every 100ms
    
    public DigitalClockElement() {
        super("Digital Clock");
        this.x = 10;
        this.y = 70;
        this.scale = 1.5f;
    }
    
    @Override
    public void render(float partialTicks) {
        if (!visible) return;
        
        long now = System.currentTimeMillis();
        if (now - lastUpdate > UPDATE_INTERVAL) {
            updateTime();
            lastUpdate = now;
        }
        
        // Rendering logic will be implemented with Minecraft render system
    }
    
    private void updateTime() {
        ZonedDateTime now = ZonedDateTime.now();
        currentTime = now.format(TIME_FORMAT);
        currentDate = now.format(DATE_FORMAT);
    }
    
    public String getCurrentTime() { return currentTime; }
    public String getCurrentDate() { return currentDate; }
}
