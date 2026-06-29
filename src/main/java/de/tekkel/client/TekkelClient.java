package de.tekkel.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tekkel.client.config.ConfigManager;
import de.tekkel.client.event.EventBus;
import de.tekkel.client.modules.ModuleManager;
import de.tekkel.client.hud.HUDManager;
import de.tekkel.client.api.DiscordRPC;
import de.tekkel.client.api.CloudSync;

/**
 * Main entry point for Tekkel Client
 * Motto: Precision. Performance. Victory.
 */
@Environment(EnvType.CLIENT)
public class TekkelClient implements ClientModInitializer {
    
    public static final String MOD_ID = "tekkel-client";
    public static final String MOD_NAME = "Tekkel Client";
    public static final String MOD_VERSION = "1.0.0";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    
    private static ConfigManager configManager;
    private static EventBus eventBus;
    private static ModuleManager moduleManager;
    private static HUDManager hudManager;
    private static DiscordRPC discordRPC;
    private static CloudSync cloudSync;
    
    @Override
    public void onInitializeClient() {
        LOGGER.info("[Tekkel Client] Initializing...");
        LOGGER.info("[Tekkel Client] Version: {}", MOD_VERSION);
        
        try {
            configManager = new ConfigManager();
            eventBus = new EventBus();
            moduleManager = new ModuleManager(eventBus);
            hudManager = new HUDManager(eventBus);
            
            LOGGER.info("[Tekkel Client] Loading configuration...");
            configManager.loadConfig();
            
            LOGGER.info("[Tekkel Client] Initializing modules...");
            moduleManager.initializeModules();
            
            LOGGER.info("[Tekkel Client] Initializing HUD system...");
            hudManager.initializeHUDElements();
            
            LOGGER.info("[Tekkel Client] Initializing Discord RPC...");
            discordRPC = new DiscordRPC();
            discordRPC.initialize();
            
            LOGGER.info("[Tekkel Client] Initializing Cloud Sync...");
            cloudSync = new CloudSync(configManager);
            cloudSync.initialize();
            
            LOGGER.info("[Tekkel Client] ✓ Successfully initialized!");
            LOGGER.info("[Tekkel Client] Precision. Performance. Victory.");
            
        } catch (Exception e) {
            LOGGER.error("[Tekkel Client] Failed to initialize!", e);
        }
    }
    
    public static ConfigManager getConfigManager() { return configManager; }
    public static EventBus getEventBus() { return eventBus; }
    public static ModuleManager getModuleManager() { return moduleManager; }
    public static HUDManager getHUDManager() { return hudManager; }
    public static DiscordRPC getDiscordRPC() { return discordRPC; }
    public static CloudSync getCloudSync() { return cloudSync; }
    public static Logger getLogger() { return LOGGER; }
}
