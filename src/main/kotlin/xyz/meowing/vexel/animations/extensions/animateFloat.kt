package xyz.meowing.vexel.animations.extensions

import xyz.meowing.vexel.animations.impl.FloatAnimation
import xyz.meowing.vexel.animations.types.AnimationTarget
import xyz.meowing.vexel.animations.types.AnimationType
import xyz.meowing.vexel.animations.types.EasingType
import xyz.meowing.vexel.components.base.VexelElement

fun <T : VexelElement<T>> T.animateFloat(
    getter: () -> Float,
    setter: (Float) -> Unit,
    endValue: Float,
    duration: Long,
    type: EasingType = EasingType.LINEAR,
    animationType: AnimationType = AnimationType.CUSTOM,
    onComplete: (() -> Unit)? = null
): FloatAnimation {
    val target = AnimationTarget(getter(), endValue, setter)
    val animation = FloatAnimation(target, duration, type, animationType, "${hashCode()}+$endValue", onComplete)
    animation.start()
    return animation
}