package de.tekkel.client.api;

import de.tekkel.client.TekkelClient;
import de.tekkel.client.config.ConfigManager;
import com.google.gson.JsonObject;
import de.tekkel.client.util.HttpUtils;

/**
 * Cloud synchronization for client settings
 */
public class CloudSync {
    
    private static final String CLOUD_API = "https://api.tekkel.de/sync";
    private final ConfigManager configManager;
    private boolean syncing = false;
    
    public CloudSync(ConfigManager configManager) {
        this.configManager = configManager;
    }
    
    public void initialize() {
        try {
            TekkelClient.getLogger().info("Initializing Cloud Sync...");
            // Cloud sync initialization
        } catch (Exception e) {
            TekkelClient.getLogger().error("Failed to initialize Cloud Sync", e);
        }
    }
    
    public void syncToCloud() {
        if (syncing) return;
        
        new Thread(() -> {
            syncing = true;
            try {
                JsonObject config = configManager.getConfig();
                String json = config.toString();
                
                HttpUtils.post(CLOUD_API + "/upload", json);
                TekkelClient.getLogger().info("Configuration synced to cloud");
            } catch (Exception e) {
                TekkelClient.getLogger().error("Failed to sync config to cloud", e);
            } finally {
                syncing = false;
            }
        }).start();
    }
    
    public void syncFromCloud() {
        if (syncing) return;
        
        new Thread(() -> {
            syncing = true;
            try {
                JsonObject cloudConfig = HttpUtils.get(CLOUD_API + "/download");
                configManager.getConfig().getAsJsonObject().keySet().forEach(key -> 
                    configManager.getConfig().add(key, cloudConfig.get(key))
                );
                TekkelClient.getLogger().info("Configuration synced from cloud");
            } catch (Exception e) {
                TekkelClient.getLogger().error("Failed to sync config from cloud", e);
            } finally {
                syncing = false;
            }
        }).start();
    }
    
    public boolean isSyncing() { return syncing; }
}
