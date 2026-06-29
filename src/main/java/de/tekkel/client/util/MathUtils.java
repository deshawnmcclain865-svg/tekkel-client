package de.tekkel.client.util;

import java.util.*;

/**
 * Utility class for mathematical operations
 */
public class MathUtils {
    
    public static float lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }
    
    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }
    
    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
    
    public static float distance(float x1, float y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }
    
    public static int getRGB(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }
    
    public static int getARGB(int a, int r, int g, int b) {
        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}
