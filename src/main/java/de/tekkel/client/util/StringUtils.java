package de.tekkel.client.util;

import java.util.*;

/**
 * Utility class for string operations
 */
public class StringUtils {
    
    public static String formatTime(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        
        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes % 60, seconds % 60);
        }
        return String.format("%02d:%02d", minutes, seconds % 60);
    }
    
    public static String formatCoordinates(double x, double y, double z) {
        return String.format("X: %.1f Y: %.1f Z: %.1f", x, y, z);
    }
    
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
    
    public static String toHexColor(int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }
}
