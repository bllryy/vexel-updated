package xyz.meowing.vexel.animations.impl

import xyz.meowing.vexel.animations.Animation
import xyz.meowing.vexel.animations.types.AnimationTarget
import xyz.meowing.vexel.animations.types.AnimationType
import xyz.meowing.vexel.animations.types.EasingType

class FloatAnimation(
    target: AnimationTarget<Float>,
    duration: Long,
    type: EasingType,
    animationType: AnimationType,
    elementId: String,
    onComplete: (() -> Unit)? = null
) : Animation<Float>(target, duration, type, animationType, elementId, onComplete) {
    override fun interpolate(start: Float, end: Float, progress: Float): Float = start + (end - start) * progress
}