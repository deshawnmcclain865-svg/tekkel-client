package de.tekkel.client.input;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages keyboard input
 */
public class KeyManager {
    
    private static final Map<Integer, Boolean> keys = new HashMap<>();
    
    public static void setKeyPressed(int key, boolean pressed) {
        keys.put(key, pressed);
    }
    
    public static boolean isKeyPressed(int key) {
        return keys.getOrDefault(key, false);
    }
    
    public static void reset() {
        keys.clear();
    }
}
