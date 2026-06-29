package de.tekkel.client.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import de.tekkel.client.TekkelClient;

/**
 * Mixin to hook into Minecraft client initialization
 */
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    
    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInitialize(CallbackInfo ci) {
        TekkelClient.getLogger().info("Minecraft Client initialized");
    }
    
    @Inject(method = "run", at = @At("HEAD"))
    private void onRun(CallbackInfo ci) {
        TekkelClient.getLogger().info("Minecraft Client started");
    }
    
    @Inject(method = "close", at = @At("HEAD"))
    private void onClose(CallbackInfo ci) {
        TekkelClient.getLogger().info("Minecraft Client closing");
        TekkelClient.getInstance().shutdown();
    }
}
