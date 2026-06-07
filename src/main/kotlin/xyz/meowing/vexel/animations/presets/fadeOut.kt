package xyz.meowing.vexel.animations.presets

import xyz.meowing.vexel.animations.extensions.animateColor
import xyz.meowing.vexel.animations.extensions.animateFloat
import xyz.meowing.vexel.animations.types.AnimationType
import xyz.meowing.vexel.animations.types.EasingType
import xyz.meowing.vexel.components.core.Rectangle
import xyz.meowing.vexel.components.core.SvgImage
import xyz.meowing.vexel.components.core.Text
import xyz.meowing.vexel.components.base.VexelElement
import java.awt.Color

fun <T : VexelElement<T>> T.fadeOut(
    duration: Long = 300,
    type: EasingType = EasingType.EASE_IN,
    includeChildren: Boolean = true,
    onComplete: (() -> Unit)? = null
): T {
    if (includeChildren) {
        children.forEach { child ->
            when (child) {
                is Rectangle -> child.fadeOut(duration, type, includeChildren)
                is Text -> child.fadeOut(duration, type, includeChildren)
                is SvgImage -> child.fadeOut(duration, type, includeChildren)
            }
        }
    }

    when (this) {
        is Rectangle -> {
            val targetBg = backgroundColor and 0x00FFFFFF
            val targetBorder = borderColor and 0x00FFFFFF
            animateColor({ backgroundColor }, { backgroundColor = it }, targetBg, duration, type) {
                visible = false
                onComplete?.invoke()
            }
            animateColor({ borderColor }, { borderColor = it }, targetBorder, duration, type)
        }
        is Text -> {
            val target = textColor and 0x00FFFFFF
            animateColor({ textColor }, { textColor = it }, target, duration, type) {
                visible = false
                onComplete?.invoke()
            }
        }
        is SvgImage -> {
            val target = color.rgb and 0x00FFFFFF
            animateColor({ color.rgb }, { setSvgColor(Color(it, true)) }, target, duration, type) {
                visible = false
                onComplete?.invoke()
            }
        }
        else -> animateFloat({ 1f }, {}, 0f, duration, type, AnimationType.ALPHA) {
            visible = false
            onComplete?.invoke()
        }
    }

    return this
}