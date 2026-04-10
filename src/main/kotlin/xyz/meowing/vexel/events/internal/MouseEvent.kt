package xyz.meowing.vexel.events.internal

import xyz.meowing.vexel.components.base.VexelElement

sealed class MouseEvent {
    class Move(
        val x: Float,
        val y: Float,
        val element: VexelElement<*>
    ) {
        class Enter(
            val x: Float,
            val y: Float,
            val element: VexelElement<*>
        )

        class Exit(
            val x: Float,
            val y: Float,
            val element: VexelElement<*>
        )
    }

    class Scroll(
        val x: Float,
        val y: Float,
        val horizontal: Double,
        val vertical: Double,
        val element: VexelElement<*>?
    )

    class Click(
        val x: Float,
        val y: Float,
        val button: Int,
        val element: VexelElement<*>?
    )

    class Release(
        val x: Float,
        val y: Float,
        val button: Int,
        val element: VexelElement<*>?
    )
}