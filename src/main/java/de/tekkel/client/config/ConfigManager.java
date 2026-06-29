package de.tekkel.client.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.tekkel.client.TekkelClient;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Manages client configuration
 */
public class ConfigManager {
    
    private final Path configDir;
    private final Path configFile;
    private JsonObject config;
    
    public ConfigManager() {
        this.configDir = Paths.get("config/tekkel-client");
        this.configFile = configDir.resolve("config.json");
        this.config = new JsonObject();
        
        try {
            Files.createDirectories(configDir);
        } catch (IOException e) {
            TekkelClient.getLogger().error("Failed to create config directory", e);
        }
    }
    
    public void loadConfig() {
        try {
            if (Files.exists(configFile)) {
                String content = Files.readString(configFile);
                config = JsonParser.parseString(content).getAsJsonObject();
                TekkelClient.getLogger().info("Configuration loaded");
            } else {
                saveConfig();
            }
        } catch (Exception e) {
            TekkelClient.getLogger().error("Failed to load config", e);
        }
    }
    
    public void saveConfig() {
        try {
            Files.writeString(configFile, config.toString());
            TekkelClient.getLogger().info("Configuration saved");
        } catch (IOException e) {
            TekkelClient.getLogger().error("Failed to save config", e);
        }
    }
    
    public JsonObject getConfig() { return config; }
    public Path getConfigDir() { return configDir; }
    public Path getConfigFile() { return configFile; }
}
