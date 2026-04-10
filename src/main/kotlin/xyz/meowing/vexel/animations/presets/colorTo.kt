package xyz.meowing.vexel.animations.presets

import xyz.meowing.vexel.animations.extensions.animateColor
import xyz.meowing.vexel.animations.types.EasingType
import xyz.meowing.vexel.components.core.Rectangle
import xyz.meowing.vexel.components.core.SvgImage
import xyz.meowing.vexel.components.core.Text
import xyz.meowing.vexel.components.base.VexelElement
import java.awt.Color

fun <T : VexelElement<T>> T.colorTo(
    color: Int,
    duration: Long = 300,
    type: EasingType = EasingType.LINEAR,
    onComplete: (() -> Unit)? = null
): T {
    when (this) {
        is Rectangle -> animateColor({ backgroundColor }, { backgroundColor = it }, color, duration, type, onComplete)
        is Text -> animateColor({ textColor }, { textColor = it }, color, duration, type, onComplete)
        is SvgImage -> animateColor({ this.color.rgb }, { setSvgColor(Color(it, true)) }, color, duration, type, onComplete)
    }
    return this
}