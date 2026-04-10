package xyz.meowing.vexel.animations.extensions

import xyz.meowing.vexel.animations.impl.VectorAnimation
import xyz.meowing.vexel.animations.types.AnimationTarget
import xyz.meowing.vexel.animations.types.AnimationType
import xyz.meowing.vexel.animations.types.EasingType
import xyz.meowing.vexel.components.base.VexelElement

fun <T : VexelElement<T>> T.animatePosition(
    endX: Float,
    endY: Float,
    duration: Long,
    type: EasingType = EasingType.LINEAR,
    onComplete: (() -> Unit)? = null
): VectorAnimation {
    val target = AnimationTarget(
        xConstraint to yConstraint,
        endX to endY
    ) { (x, y) ->
        xConstraint = x
        yConstraint = y
    }
    val animation = VectorAnimation(target, duration, type, AnimationType.POSITION, "${hashCode()}:pos", onComplete)
    animation.start()
    return animation
}