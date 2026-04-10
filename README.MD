# Vexel

Vexel is a modern, declarative GUI library for Minecraft mods that simplifies interface creation. Built on top of NanoVG, it offers both high-level declarative components and low-level rendering capabilities. Heavily inspired by Elementa, Vexel provides a clean API for creating responsive, animated user interfaces.

This is a maintained fork by [bllryy](https://github.com/bllryy) updated to support Minecraft 1.21.11.

Have questions? Join our [discord!](https://discord.gg/QHFWnydFXr)

## Features

- **Declarative UI**: Build interfaces by describing what you want, not how to position it
- **Layout System**: Position and sizing with automatic calculations
- **Animation Support**: Built-in animations with presets
- **Component Library**: Pre-built elements like buttons, inputs, sliders, and more
- **NanoVG Integration**: Direct access to powerful vector graphics rendering

## Versions

[![JitPack](https://jitpack.io/v/bllryy/vexel-updated.svg)](https://jitpack.io/#bllryy/vexel-updated)

| Version | Loaders |
|---|---|
| 1.21.5 | Fabric |
| 1.21.8 | Fabric |
| 1.21.10 | Fabric |
| 1.21.11 | Fabric |

## Installation

Add Vexel to your `build.gradle.kts`:

```kotlin
repositories {
    maven("https://jitpack.io")
    // Required for Deftu's Gradle Toolkit (if you use it)
    maven("https://maven.deftu.dev/releases")
    maven("https://maven.deftu.dev/snapshots")
}

dependencies {
    // Replace TAG with the release version (e.g. 126)
    // Replace mc-version with your target (e.g. 1.21.11-fabric)
    modImplementation(include("xyz.meowing:vexel-<mc-version>:<TAG>")!!)
}
```

For example, for Minecraft 1.21.11 on Fabric:
```kotlin
modImplementation(include("xyz.meowing:vexel-1.21.11-fabric:126")!!)
```

## Quick Start

Create a simple screen with Vexel:

```kotlin
class MyScreen : VexelScreen() {
    override fun afterInitialization() {
        Button("Click me!")
            .setSizing(100f, Size.Pixels, 30f, Size.Pixels)
            .setPositioning(50f, Pos.ParentPixels, 50f, Pos.ParentPixels)
            .onClick { mouseX, mouseY, button ->
                println("Button clicked!")
                true // indicates the event was handled
            }
            .childOf(window)
    }
}
```

Elements can also be manually rendered outside of the standard screen hierarchy using `.drawAsRoot()`.
This is useful for rendering custom overlays such as scoreboards, HUD elements, or menus that appear on top of the game without pausing it.

```kotlin
val square = Rectangle()
            .backgroundColor(0xFFFF0000.toInt())
            .setSizing(100f, Size.Pixels, 100f, Size.Pixels)
            .setPositioning(0f, Pos.ParentCenter, 0f, Pos.ParentCenter)

fun someEvent() {
    square.drawAsRoot()
}
```

## Core Concepts

### Elements and Components

All UI elements extend `VexelElement<T>`, providing:
- **Positioning**: Flexible layout system
- **Sizing**: Auto, percentage, or pixel-based dimensions
- **Event Handling**: Mouse and keyboard interaction support
- **Parent-Child Relationships**: Hierarchical organization
- **Animation Support**: Built-in animation capabilities

### Layout System

Vexel uses positioning and sizing methods for layout control:

```kotlin
element
    .setSizing(200f, Size.Pixels, 100f, Size.Pixels)
    .setPositioning(10f, Pos.ParentPixels, 20f, Pos.ParentCenter)
```

#### Positioning Types (`Pos`)
- `ParentPixels`: Offset in pixels from parent
- `ScreenPixels`: Absolute screen coordinates
- `ParentPercent`: Percentage of parent dimensions
- `ScreenPercent`: Percentage of screen dimensions
- `ParentCenter`: Centered within parent
- `ScreenCenter`: Centered on screen
- `AfterSibling`: After the previous sibling element
- `MatchSibling`: Match position of previous sibling

#### Sizing Types (`Size`)
- `Pixels`: Fixed pixel size
- `ParentPerc`: Percentage of parent size, or screen size if no parent
- `Auto`: Automatic sizing based on content

### Animation System

Vexel provides a comprehensive animation system:

```kotlin
// Basic animations
element.animatePosition(100f, 50f, 500, EasingType.EASE_OUT)
element.animateSize(200f, 100f, 300, EasingType.EASE_IN_OUT)

// Animation presets
element.fadeIn(duration = 300)
element.slideIn(fromX = -100f, duration = 500)
element.bounceScale(scale = 1.2f, duration = 200)
```

#### Animation Types: `LINEAR`, `EASE_IN`, `EASE_OUT`, `EASE_IN_OUT`

## Event Handling

Vexel provides comprehensive event handling:

```kotlin
element
    .onMouseEnter { mouseX, mouseY ->
        // Mouse entered element
    }
    .onMouseExit { mouseX, mouseY ->
        // Mouse left element
    }
    .onClick { mouseX, mouseY, button ->
        // Element clicked
        true // Return true if handled
    }
    .onScroll { mouseX, mouseY, horizontal, vertical ->
        // Scroll event
        true
    }
    .onCharType { keyCode, scanCode, char ->
        // Character typed
        true
    }
    .onValueChange { value ->
        // Value changed for input elements
    }
```

## Documentation

- [Component Reference](docs/Components.md)
- [Element Reference](docs/Elements.md)
- [Advanced Usage](docs/Advanced.md)

## License

Vexel is licensed under the GNU General Public License v3.0. See [LICENSE](LICENSE) for more information.

## Credits

- **Deftu** - Deftu's Gradle Toolkit and original publishing infrastructure
- **meowing-xyz** - Original Vexel library
- **bllryy** - 1.21.11 update and current maintenance
