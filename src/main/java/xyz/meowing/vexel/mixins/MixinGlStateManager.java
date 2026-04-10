package xyz.meowing.vexel.mixins;

import com.mojang.blaze3d.opengl.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.meowing.vexel.api.nvg.StateTracker;

@Mixin(GlStateManager.class)
public class MixinGlStateManager {
    @Inject(method = "_bindTexture", at = @At("HEAD"), remap = false)
    private static void onBindTexture(int texture, CallbackInfo ci) {
        StateTracker.setPreviousBoundTexture(texture);
    }
}