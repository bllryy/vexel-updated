package xyz.meowing.vexel.animations.impl

import xyz.meowing.vexel.animations.Animation
import xyz.meowing.vexel.animations.types.AnimationTarget
import xyz.meowing.vexel.animations.types.AnimationType
import xyz.meowing.vexel.animations.types.EasingType

class VectorAnimation(
    target: AnimationTarget<Pair<Float, Float>>,
    duration: Long,
    type: EasingType,
    animationType: AnimationType,
    elementId: String,
    onComplete: (() -> Unit)? = null
) : Animation<Pair<Float, Float>>(target, duration, type, animationType, elementId, onComplete) {
    override fun interpolate(start: Pair<Float, Float>, end: Pair<Float, Float>, progress: Float): Pair<Float, Float> {
        return (start.first + (end.first - start.first) * progress) to (start.second + (end.second - start.second) * progress)
    }
}