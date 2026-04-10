package xyz.meowing.vexel.animations.presets

import xyz.meowing.vexel.animations.extensions.animatePosition
import xyz.meowing.vexel.animations.types.EasingType
import xyz.meowing.vexel.components.base.VexelElement

private val originalPositions = mutableMapOf<String, Pair<Float, Float>>()

fun <T : VexelElement<T>> T.slideIn(
    fromX: Float = -width,
    fromY: Float = 0f,
    duration: Long = 500,
    type: EasingType = EasingType.EASE_OUT,
    onComplete: (() -> Unit)? = null
): T {
    val id = hashCode().toString()
    originalPositions.putIfAbsent(id, xConstraint to yConstraint)

    val (targetX, targetY) = originalPositions[id]!!
    xConstraint = fromX
    yConstraint = fromY
    visible = true

    animatePosition(targetX, targetY, duration, type, onComplete)
    return this
}
