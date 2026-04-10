package xyz.meowing.vexel.mixins;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
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
    //#if MC >= 1.21.6
    //$$ @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/render/GuiRenderer;incrementFrame()V", shift = At.Shift.AFTER), cancellable = true)
    //#else
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;draw()V", shift = At.Shift.AFTER), cancellable = true)
    //#endif

    public void hookRender(
            RenderTickCounter tickCounter,
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