package de.tekkel.client.hud;

import com.google.gson.JsonObject;

/**
 * Base class for HUD elements
 */
public abstract class HUDElement {
    
    protected String name;
    protected float x = 0;
    protected float y = 0;
    protected float scale = 1.0f;
    protected float alpha = 1.0f;
    protected int color = 0xFFFFFF;
    protected boolean visible = true;
    
    public HUDElement(String name) {
        this.name = name;
    }
    
    public abstract void render(float partialTicks);
    
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("x", x);
        json.addProperty("y", y);
        json.addProperty("scale", scale);
        json.addProperty("alpha", alpha);
        json.addProperty("color", color);
        json.addProperty("visible", visible);
        return json;
    }
    
    public void fromJson(JsonObject json) {
        if (json.has("x")) x = json.get("x").getAsFloat();
        if (json.has("y")) y = json.get("y").getAsFloat();
        if (json.has("scale")) scale = json.get("scale").getAsFloat();
        if (json.has("alpha")) alpha = json.get("alpha").getAsFloat();
        if (json.has("color")) color = json.get("color").getAsInt();
        if (json.has("visible")) visible = json.get("visible").getAsBoolean();
    }
    
    public String getName() { return name; }
    public float getX() { return x; }
    public void setX(float x) { this.x = x; }
    public float getY() { return y; }
    public void setY(float y) { this.y = y; }
    public float getScale() { return scale; }
    public void setScale(float scale) { this.scale = scale; }
    public float getAlpha() { return alpha; }
    public void setAlpha(float alpha) { this.alpha = Math.max(0, Math.min(1, alpha)); }
    public int getColor() { return color; }
    public void setColor(int color) { this.color = color; }
    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }
}
