package xyz.meowing.vexel.animations.extensions

import xyz.meowing.vexel.animations.impl.ColorAnimation
import xyz.meowing.vexel.animations.types.AnimationTarget
import xyz.meowing.vexel.animations.types.EasingType
import xyz.meowing.vexel.components.base.VexelElement

fun <T : VexelElement<T>> T.animateColor(
    getter: () -> Int,
    setter: (Int) -> Unit,
    endValue: Int,
    duration: Long,
    type: EasingType = EasingType.LINEAR,
    onComplete: (() -> Unit)? = null
): ColorAnimation {
    val target = AnimationTarget(getter(), endValue, setter)
    val animation = ColorAnimation(target, duration, type, "${hashCode()}+$endValue", onComplete)
    animation.start()
    return animation
}