package de.tekkel.client.input;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages mouse input
 */
public class MouseManager {
    
    private static double mouseX = 0;
    private static double mouseY = 0;
    private static boolean leftPressed = false;
    private static boolean rightPressed = false;
    private static int clicks = 0;
    private static long lastClickTime = 0;
    
    public static void setMousePos(double x, double y) {
        mouseX = x;
        mouseY = y;
    }
    
    public static void setLeftPressed(boolean pressed) {
        if (pressed && !leftPressed) {
            clicks++;
            lastClickTime = System.currentTimeMillis();
        }
        leftPressed = pressed;
    }
    
    public static void setRightPressed(boolean pressed) {
        rightPressed = pressed;
    }
    
    public static int getCPS() {
        long now = System.currentTimeMillis();
        if (now - lastClickTime > 1000) {
            int cps = clicks;
            clicks = 0;
            return cps;
        }
        return clicks;
    }
    
    public static double getMouseX() { return mouseX; }
    public static double getMouseY() { return mouseY; }
    public static boolean isLeftPressed() { return leftPressed; }
    public static boolean isRightPressed() { return rightPressed; }
}
