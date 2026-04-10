package xyz.meowing.vexel.animations.impl

import xyz.meowing.vexel.animations.Animation
import xyz.meowing.vexel.animations.types.AnimationTarget
import xyz.meowing.vexel.animations.types.AnimationType
import xyz.meowing.vexel.animations.types.EasingType

class ColorAnimation(
    target: AnimationTarget<Int>,
    duration: Long,
    type: EasingType,
    elementId: String,
    onComplete: (() -> Unit)? = null
) : Animation<Int>(target, duration, type, AnimationType.COLOR, elementId, onComplete) {
    override fun interpolate(start: Int, end: Int, progress: Float): Int {
        val startA = (start shr 24) and 0xFF
        val startR = (start shr 16) and 0xFF
        val startG = (start shr 8) and 0xFF
        val startB = start and 0xFF

        val endA = (end shr 24) and 0xFF
        val endR = (end shr 16) and 0xFF
        val endG = (end shr 8) and 0xFF
        val endB = end and 0xFF

        val a = (startA + (endA - startA) * progress).toInt()
        val r = (startR + (endR - startR) * progress).toInt()
        val g = (startG + (endG - startG) * progress).toInt()
        val b = (startB + (endB - startB) * progress).toInt()

        return (a shl 24) or (r shl 16) or (g shl 8) or b
    }
}
