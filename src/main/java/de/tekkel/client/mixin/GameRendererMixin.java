package de.tekkel.client.mixin;

import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import de.tekkel.client.event.RenderEvent;
import de.tekkel.client.TekkelClient;

/**
 * Mixin to hook into game rendering
 */
@Mixin(GameRenderer.class)
public class GameRendererMixin {
    
    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(float tickDelta, long startTime, boolean tick, CallbackInfo ci) {
        RenderEvent event = new RenderEvent(tickDelta);
        TekkelClient.getEventBus().post(event);
    }
}
