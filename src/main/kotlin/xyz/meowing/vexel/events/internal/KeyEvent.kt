package xyz.meowing.vexel.events.internal

import xyz.meowing.vexel.components.base.VexelElement

sealed class KeyEvent {
    class Type(
        val keyCode: Int,
        val scanCode: Int,
        val char: Char,
        val element: VexelElement<*>
    )
}