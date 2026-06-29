package de.tekkel.client.mixin;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import de.tekkel.client.event.AttackEvent;
import de.tekkel.client.TekkelClient;

/**
 * Mixin to hook into player attacks
 */
@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    
    @Inject(method = "attackEntity", at = @At("HEAD"))
    private void onAttack(net.minecraft.client.network.ClientPlayerEntity player, Entity entity, CallbackInfo ci) {
        AttackEvent event = new AttackEvent(player, entity);
        TekkelClient.getEventBus().post(event);
    }
}
