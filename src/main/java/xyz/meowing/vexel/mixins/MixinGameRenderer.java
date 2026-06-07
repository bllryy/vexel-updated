package xyz.meowing.vexel.mixins;

import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.DeltaTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.meowing.knit.api.render.KnitResolution;
import xyz.meowing.vexel.Vexel;
import xyz.meowing.vexel.events.GuiEvent;

import static xyz.meowing.vexel.Vexel.getEventBus;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    // 26.1.2 GUI overhaul: the vanilla GUI is drawn by GuiRenderer.render(GpuBufferSlice) inside
    // GameRenderer.render(DeltaTracker, boolean). Hook right after it so Vexel/NanoVG draws on top.
    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/render/GuiRenderer;render(Lcom/mojang/blaze3d/buffers/GpuBufferSlice;)V",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    public void hookRender(
            DeltaTracker tickCounter,
            boolean tick,
            CallbackInfo ci
    ) {
        Vexel.getRenderer().beginFrame(KnitResolution.getWindowWidth(), KnitResolution.getWindowHeight());
        if (
                getEventBus().post(
                        new GuiEvent.Render(),
                        false
                )
        ) ci.cancel();
        Vexel.getRenderer().endFrame();
    }
}
