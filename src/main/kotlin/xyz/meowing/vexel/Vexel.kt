package xyz.meowing.vexel

import net.minecraft.util.Identifier
import xyz.meowing.knit.api.KnitClient.client
import xyz.meowing.knit.api.events.EventBus
import xyz.meowing.vexel.api.RenderAPI
import xyz.meowing.vexel.api.nvg.NVGRenderer
import xyz.meowing.vexel.api.style.Font

object Vexel {
    private var _renderer: RenderAPI? = null

    @JvmStatic
    val defaultFont = Font("Default", client.resourceManager.getResource(Identifier.of("vexel", "font.ttf")).get().inputStream)

    @JvmStatic
    val eventBus = EventBus()

    @JvmStatic
    val renderer: RenderAPI
        get() = _renderer ?: NVGRenderer

    fun init(renderer: RenderAPI) {
        _renderer = renderer
    }
}
