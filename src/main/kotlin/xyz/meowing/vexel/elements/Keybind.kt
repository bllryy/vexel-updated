package xyz.meowing.vexel.elements

import xyz.meowing.knit.api.input.KnitInputs
import xyz.meowing.knit.api.input.KnitKeys
import xyz.meowing.vexel.components.core.Rectangle
import xyz.meowing.vexel.components.core.Text
import xyz.meowing.vexel.components.base.enums.Pos
import xyz.meowing.vexel.components.base.enums.Size
import xyz.meowing.vexel.components.base.VexelElement

class Keybind(
    backgroundColor: Int = 0x80404040.toInt(),
    borderColor: Int = 0xFF606060.toInt(),
    borderRadius: Float = 4f,
    borderThickness: Float = 1f,
    padding: FloatArray = floatArrayOf(12f, 24f, 12f, 24f),
    hoverColor: Int? = 0x80505050.toInt(),
    pressedColor: Int? = 0x80303030.toInt(),
    widthType: Size = Size.Auto,
    heightType: Size = Size.Auto
) : VexelElement<Keybind>(widthType, heightType) {
    var selectedKeyId: Int? = null
    var selectedScanId: Int? = null
    var listen: Boolean = false

    val background = Rectangle(
        backgroundColor,
        borderColor,
        borderRadius,
        borderThickness,
        padding,
        hoverColor,
        pressedColor,
        Size.Percent,
        Size.Percent
    )
        .setSizing(100f, Size.Percent, 100f, Size.Percent)
        .ignoreMouseEvents()
        .childOf(this)

    val innerText = Text(getKeyName(KnitKeys.KEY_A.code, 0), 0xFFFFFFFF.toInt(), 12f)
        .setPositioning(Pos.ParentCenter, Pos.ParentCenter)
        .childOf(background)

    init {
        setSizing(100f, Size.Pixels, 0f, Size.Auto)
        setPositioning(Pos.ParentPixels, Pos.ParentPixels)
        ignoreFocus()

        onClick { _ ->
            listenForKeybind()
            true
        }

        onCharType { event ->
            if (!listen) return@onCharType false

            if (event.keyCode == 256) {
                innerText.text = "None"
                selectedKeyId = null
                selectedScanId = null
            } else {
                innerText.text = getKeyName(event.keyCode, event.scanCode)
                selectedKeyId = event.keyCode
                selectedScanId = event.scanCode
            }

            onValueChange.forEach { it.invoke(event.keyCode) }
            listen = false
            true
        }
    }

    fun listenForKeybind() {
        innerText.text = "Press a key.."
        listen = true
    }

    override fun onRender(mouseX: Float, mouseY: Float) {
        background.isHovered = isHovered
        background.isPressed = isPressed
    }

    private fun getKeyName(keyCode: Int, scanCode: Int): String = when(keyCode) {
        0 -> "None"
        else -> KnitInputs.getDisplayName(keyCode, scanCode)
    }

    override fun getAutoWidth(): Float = background.getAutoWidth()
    override fun getAutoHeight(): Float = background.getAutoHeight()

    fun padding(top: Float, right: Float, bottom: Float, left: Float): Keybind = apply {
        background.padding(top, right, bottom, left)
    }

    fun padding(all: Float): Keybind = apply {
        background.padding(all)
    }

    fun backgroundColor(color: Int): Keybind = apply {
        background.backgroundColor(color)
    }

    fun borderColor(color: Int): Keybind = apply {
        background.borderColor(color)
    }

    fun borderRadius(radius: Float): Keybind = apply {
        background.borderRadius(radius)
    }

    fun borderThickness(thickness: Float): Keybind = apply {
        background.borderThickness(thickness)
    }

    fun hoverColor(color: Int): Keybind = apply {
        background.hoverColor(color)
    }

    fun pressedColor(color: Int): Keybind = apply {
        background.pressedColor(color)
    }
}