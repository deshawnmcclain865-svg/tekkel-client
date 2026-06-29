package de.tekkel.client.mixin;

import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import de.tekkel.client.event.ScreenEvent;
import de.tekkel.client.TekkelClient;

/**
 * Mixin to hook into screen changes
 */
@Mixin(Screen.class)
public class ScreenMixin {
    
    @Inject(method = "init", at = @At("TAIL"))
    private void onScreenOpen(CallbackInfo ci) {
        ScreenEvent.Open event = new ScreenEvent.Open((Screen)(Object)this);
        TekkelClient.getEventBus().post(event);
    }
    
    @Inject(method = "close", at = @At("HEAD"))
    private void onScreenClose(CallbackInfo ci) {
        ScreenEvent.Close event = new ScreenEvent.Close();
        TekkelClient.getEventBus().post(event);
    }
}
