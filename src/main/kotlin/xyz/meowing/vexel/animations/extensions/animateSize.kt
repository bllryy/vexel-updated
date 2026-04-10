package xyz.meowing.vexel.animations.extensions

import xyz.meowing.vexel.animations.impl.VectorAnimation
import xyz.meowing.vexel.animations.types.AnimationTarget
import xyz.meowing.vexel.animations.types.AnimationType
import xyz.meowing.vexel.animations.types.EasingType
import xyz.meowing.vexel.components.base.VexelElement

fun <T : VexelElement<T>> T.animateSize(
    endWidth: Float,
    endHeight: Float,
    duration: Long,
    type: EasingType = EasingType.LINEAR,
    onComplete: (() -> Unit)? = null
): VectorAnimation {
    val target = AnimationTarget(
        width to height,
        endWidth to endHeight
    ) { (w, h) ->
        width = w
        height = h
    }
    val animation = VectorAnimation(target, duration, type, AnimationType.SIZE, "${hashCode()}:size", onComplete)
    animation.start()
    return animation
}