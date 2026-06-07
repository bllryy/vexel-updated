package xyz.meowing.vexel.animations.presets

import xyz.meowing.vexel.animations.extensions.animateSize
import xyz.meowing.vexel.animations.types.EasingType
import xyz.meowing.vexel.components.base.VexelElement

fun <T : VexelElement<T>> T.scaleTo(
    width: Float,
    height: Float,
    duration: Long = 300,
    type: EasingType = EasingType.EASE_OUT,
    onComplete: (() -> Unit)? = null
): T {
    animateSize(width, height, duration, type, onComplete)
    return this
}