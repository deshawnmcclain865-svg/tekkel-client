package de.tekkel.client.hud;

import com.google.gson.JsonObject;

public abstract class HUDElement {
    public String name;
    public float x = 0;
    public float y = 0;
    public float scale = 1.0f;
    public int color = 0xFFFFFF;
    public boolean visible = true;

    public HUDElement(String name) {
        this.name = name;
    }

    public abstract void render(float partialTicks);

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("name", name);
        json.addProperty("x", x);
        json.addProperty("y", y);
        json.addProperty("scale", scale);
        json.addProperty("color", color);
        json.addProperty("visible", visible);
        return json;
    }
}
