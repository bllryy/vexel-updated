package xyz.meowing.vexel.animations.presets

import xyz.meowing.vexel.animations.AnimationManager
import xyz.meowing.vexel.animations.extensions.animateSize
import xyz.meowing.vexel.animations.types.AnimationType
import xyz.meowing.vexel.animations.types.EasingType
import xyz.meowing.vexel.components.base.VexelElement

private val originalSizes = mutableMapOf<String, Pair<Float, Float>>()

fun <T : VexelElement<T>> T.bounceScale(
    scale: Float = 1.2f,
    duration: Long = 200,
    onComplete: (() -> Unit)? = null
): T {
    val id = hashCode().toString()
    AnimationManager.stopAnimations(id, AnimationType.SIZE)
    originalSizes.putIfAbsent(id, width to height)

    val (originalWidth, originalHeight) = originalSizes[id]!!
    val targetWidth = originalWidth * scale
    val targetHeight = originalHeight * scale

    animateSize(targetWidth, targetHeight, duration / 2, EasingType.EASE_OUT) {
        animateSize(originalWidth, originalHeight, duration / 2, EasingType.EASE_IN, onComplete)
    }

    return this
}