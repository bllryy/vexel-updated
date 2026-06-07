package xyz.meowing.vexel.api

import xyz.meowing.vexel.Vexel.defaultFont
import xyz.meowing.vexel.api.style.Font
import xyz.meowing.vexel.api.style.Gradient
import xyz.meowing.vexel.api.style.Image
import java.awt.Color

interface RenderAPI {
    fun beginFrame(width: Float, height: Float)
    fun endFrame()

    fun push()
    fun pop()

    fun scale(x: Float, y: Float)
    fun translate(x: Float, y: Float)
    fun rotate(amount: Float)
    fun globalAlpha(amount: Float)

    fun pushScissor(x: Float, y: Float, w: Float, h: Float)
    fun popScissor()

    fun line(x1: Float, y1: Float, x2: Float, y2: Float, thickness: Float, color: Int)

    fun rect(x: Float, y: Float, w: Float, h: Float, color: Int)
    fun rect(x: Float, y: Float, w: Float, h: Float, color: Int, radius: Float)
    fun rect(x: Float, y: Float, w: Float, h: Float, color: Int, radius: Float, roundTop: Boolean)
    fun rect(x: Float, y: Float, w: Float, h: Float, color: Int, topRight: Float, topLeft: Float, bottomRight: Float, bottomLeft: Float)
    fun gradientRect(x: Float, y: Float, w: Float, h: Float, color1: Int, color2: Int, gradient: Gradient, radius: Float)

    fun hollowRect(x: Float, y: Float, w: Float, h: Float, thickness: Float, color: Int, radius: Float)
    fun hollowGradientRect(x: Float, y: Float, w: Float, h: Float, thickness: Float, color1: Int, color2: Int, gradient: Gradient, radius: Float)

    fun circle(x: Float, y: Float, radius: Float, color: Int)

    fun dropShadow(x: Float, y: Float, width: Float, height: Float, blur: Float, spread: Float, shadowColor: Color, radius: Float)

    fun text(text: String, x: Float, y: Float, size: Float, color: Int, font: Font = defaultFont)
    fun wrappedText(text: String, x: Float, y: Float, w: Float, size: Float, color: Int, font: Font = defaultFont, lineHeight: Float = 1f)
    fun shadowedText(text: String, x: Float, y: Float, size: Float, color: Int, font: Font = defaultFont, shadowColor: Int = 0x80000000.toInt(), offsetX: Float = 1.5f, offsetY: Float = 1.5f, blur: Float = 2f)

    fun textWidth(text: String, size: Float, font: Font = defaultFont): Float
    fun textBounds(text: String, w: Float, size: Float, font: Font = defaultFont, lineHeight: Float = 1f): FloatArray

    fun image(image: Int, textureWidth: Int, textureHeight: Int, subX: Int, subY: Int, subW: Int, subH: Int, x: Float, y: Float, w: Float, h: Float, radius: Float)
    fun image(image: Image, x: Float, y: Float, w: Float, h: Float, radius: Float)
    fun image(image: Image, x: Float, y: Float, w: Float, h: Float)

    fun svg(id: String, x: Float, y: Float, w: Float, h: Float, a: Float = 1f)

    fun createImage(resourcePath: String, width: Int = -1, height: Int = -1, color: Color = Color.WHITE, id: String): Image
    fun deleteImage(image: Image)

    fun cleanCache()
}