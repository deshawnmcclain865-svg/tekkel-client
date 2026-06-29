package de.tekkel.client.hud.impl;

import de.tekkel.client.hud.HUDElement;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import com.google.gson.JsonObject;

/**
 * Countdown Timer HUD Element
 */
public class CountdownTimerElement extends HUDElement {
    
    private static final DateTimeFormatter TIMER_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    private long targetTimeMillis = 0;
    private String countdownDisplay = "00:00:00";
    private boolean isRunning = false;
    private long lastUpdate = 0;
    private static final long UPDATE_INTERVAL = 100;
    
    public CountdownTimerElement() {
        super("Countdown Timer");
        this.x = 10;
        this.y = 115;
        this.scale = 1.3f;
        this.color = 0xFF0000; // Red color for timer
    }
    
    @Override
    public void render(float partialTicks) {
        if (!visible) return;
        
        if (isRunning) {
            long now = System.currentTimeMillis();
            if (now - lastUpdate > UPDATE_INTERVAL) {
                updateCountdown();
                lastUpdate = now;
            }
        }
        
        // Rendering logic will be implemented here
    }
    
    private void updateCountdown() {
        long now = System.currentTimeMillis();
        long remaining = Math.max(0, targetTimeMillis - now);
        
        if (remaining <= 0) {
            isRunning = false;
            countdownDisplay = "00:00:00";
            onTimerComplete();
            return;
        }
        
        long hours = remaining / 3600000;
        long minutes = (remaining % 3600000) / 60000;
        long seconds = (remaining % 60000) / 1000;
        
        countdownDisplay = String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    
    public void startTimer(long durationMillis) {
        targetTimeMillis = System.currentTimeMillis() + durationMillis;
        isRunning = true;
    }
    
    public void stopTimer() {
        isRunning = false;
    }
    
    public void resetTimer() {
        isRunning = false;
        countdownDisplay = "00:00:00";
        targetTimeMillis = 0;
    }
    
    private void onTimerComplete() {
        // Timer completed - can add sound/notification here
    }
    
    @Override
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        json.addProperty("countdownDisplay", countdownDisplay);
        json.addProperty("isRunning", isRunning);
        json.addProperty("targetTimeMillis", targetTimeMillis);
        return json;
    }
    
    public String getCountdownDisplay() { return countdownDisplay; }
    public boolean isRunning() { return isRunning; }
}
