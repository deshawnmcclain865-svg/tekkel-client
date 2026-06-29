package de.tekkel.client.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import de.tekkel.client.event.TickEvent;
import de.tekkel.client.TekkelClient;

/**
 * Mixin to hook into client player ticks
 */
@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    
    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        TickEvent event = new TickEvent();
        TekkelClient.getEventBus().post(event);
    }
}
