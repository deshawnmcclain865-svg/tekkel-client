package de.tekkel.client.hud.impl;

import de.tekkel.client.hud.HUDElement;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Session Timer HUD Element - Tracks playtime
 */
public class SessionTimerElement extends HUDElement {
    
    private static final DateTimeFormatter SESSION_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    private long sessionStartTime = 0;
    private String sessionDuration = "00:00:00";
    private boolean sessionActive = false;
    private long lastUpdate = 0;
    private static final long UPDATE_INTERVAL = 1000; // Update every second
    
    public SessionTimerElement() {
        super("Session Timer");
        this.x = 10;
        this.y = 130;
        this.scale = 1.0f;
        this.color = 0x00FF00; // Green color
    }
    
    @Override
    public void render(float partialTicks) {
        if (!visible) return;
        
        if (sessionActive) {
            long now = System.currentTimeMillis();
            if (now - lastUpdate > UPDATE_INTERVAL) {
                updateSessionTime();
                lastUpdate = now;
            }
        }
        
        // Rendering logic will be implemented here
    }
    
    private void updateSessionTime() {
        if (sessionStartTime == 0) return;
        
        long elapsed = System.currentTimeMillis() - sessionStartTime;
        long hours = elapsed / 3600000;
        long minutes = (elapsed % 3600000) / 60000;
        long seconds = (elapsed % 60000) / 1000;
        
        sessionDuration = String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    
    public void startSession() {
        if (!sessionActive) {
            sessionStartTime = System.currentTimeMillis();
            sessionActive = true;
        }
    }
    
    public void endSession() {
        sessionActive = false;
    }
    
    public void resetSession() {
        sessionActive = false;
        sessionStartTime = 0;
        sessionDuration = "00:00:00";
    }
    
    public String getSessionDuration() { return sessionDuration; }
    public boolean isSessionActive() { return sessionActive; }
    public long getSessionStartTime() { return sessionStartTime; }
}
