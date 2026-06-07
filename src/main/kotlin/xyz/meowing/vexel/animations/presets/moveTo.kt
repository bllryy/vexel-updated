package xyz.meowing.vexel.animations.presets

import xyz.meowing.vexel.animations.extensions.animatePosition
import xyz.meowing.vexel.animations.types.EasingType
import xyz.meowing.vexel.components.base.VexelElement

fun <T : VexelElement<T>> T.moveTo(
    x: Float,
    y: Float,
    duration: Long = 500,
    type: EasingType = EasingType.EASE_OUT,
    onComplete: (() -> Unit)? = null
): T {
    animatePosition(x, y, duration, type, onComplete)
    return this
}