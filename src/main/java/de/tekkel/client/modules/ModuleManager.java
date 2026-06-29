package de.tekkel.client.modules;

import java.util.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import de.tekkel.client.event.EventBus;
import de.tekkel.client.modules.impl.pvp.*;
import de.tekkel.client.modules.impl.comfort.*;
import de.tekkel.client.modules.impl.performance.*;

/**
 * Manages all modules in the client
 */
public class ModuleManager {
    
    private final List<Module> modules = new ArrayList<>();
    private final EventBus eventBus;
    
    public ModuleManager(EventBus eventBus) {
        this.eventBus = eventBus;
    }
    
    public void initializeModules() {
        // PvP Modules
        addModule(new CrosshairModule());
        addModule(new KeystrokesModule());
        addModule(new CPSCounterModule());
        addModule(new FPSCounterModule());
        addModule(new PingDisplayModule());
        addModule(new ReachDisplayModule());
        addModule(new ComboCounterModule());
        addModule(new ArmorStatusModule());
        addModule(new PotionEffectsModule());
        addModule(new CoordinatesModule());
        addModule(new DirectionHUDModule());
        addModule(new ClockModule());
        addModule(new AttackCooldownModule());
        addModule(new ShieldCooldownModule());
        addModule(new TotemCounterModule());
        addModule(new InventoryHUDModule());
        addModule(new HotbarHUDModule());
        addModule(new ItemCooldownModule());
        
        // Comfort Modules
        addModule(new ToggleSprintModule());
        addModule(new ToggleSneakModule());
        addModule(new ZoomModule());
        addModule(new FreelookModule());
        addModule(new PerspectiveModule());
        addModule(new FullbrightModule());
        addModule(new BetterChatModule());
        addModule(new ItemPhysicsModule());
        addModule(new HitColorModule());
        addModule(new DamageTintModule());
        
        // Performance Modules
        addModule(new EntityCullingModule());
        addModule(new DynamicFPSModule());
        addModule(new SmartRenderingModule());
        addModule(new ParticleOptimizerModule());
        addModule(new ChunkOptimizerModule());
        addModule(new MemoryOptimizerModule());
        addModule(new BetterCloudsModule());
        addModule(new BetterFogModule());
        addModule(new SmartLeavesModule());
    }
    
    public void addModule(Module module) {
        module.setEventBus(eventBus);
        modules.add(module);
    }
    
    public List<Module> getModules() {
        return new ArrayList<>(modules);
    }
    
    public List<Module> getModulesByCategory(ModuleCategory category) {
        return modules.stream()
            .filter(m -> m.getCategory() == category)
            .toList();
    }
    
    public Module getModule(String name) {
        return modules.stream()
            .filter(m -> m.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }
    
    public JsonObject saveConfig() {
        JsonObject json = new JsonObject();
        JsonArray array = new JsonArray();
        
        for (Module module : modules) {
            JsonObject moduleJson = new JsonObject();
            moduleJson.addProperty("name", module.getName());
            moduleJson.add("config", module.toJson());
            array.add(moduleJson);
        }
        
        json.add("modules", array);
        return json;
    }
    
    public void loadConfig(JsonObject json) {
        if (!json.has("modules")) return;
        
        JsonArray array = json.getAsJsonArray("modules");
        for (int i = 0; i < array.size(); i++) {
            JsonObject moduleJson = array.get(i).getAsJsonObject();
            String name = moduleJson.get("name").getAsString();
            
            Module module = getModule(name);
            if (module != null && moduleJson.has("config")) {
                module.fromJson(moduleJson.getAsJsonObject("config"));
            }
        }
    }
}
