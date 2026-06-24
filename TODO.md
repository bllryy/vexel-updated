  ---
  Option 1 — Add NVG_DEBUG to find the root cause (quick)

  In Vexel's NVGRenderer static block, change:
  nvgCreate(3)  // NVG_ANTIALIAS | NVG_STENCIL_STROKES
  to:
  nvgCreate(7)  // + NVG_DEBUG (flag 4)
  NanoVG will then print internal errors to stdout. This will tell you exactly why nvgCreateFontMem is returning -1 (if it is) — likely a font stash texture creation failure with your AMD Mesa setup.

  ---
  Option 2 — Replace NVG text with Minecraft's TextRenderer (most reliable)

  This is the safest fix since MC's TextRenderer works with any pipeline including Sodium. The strategy: keep NVG for shapes, swap text out.

  In NVGRenderer.text() in Vexel, instead of calling nvgText, you'd call MC's renderer. But this can't happen inside an NVG frame cleanly. The approach that works:

  In VexelScreen's event handler (onInitGui$lambda$0), split rendering into two phases:
  1. NVG frame: beginFrame → window.draw() shapes only → onRenderGui() → endFrame
  2. MC text phase: render all queued text strings using DrawContext.drawText()

  This requires adding a queueText(...) API to NVGRenderer/VexelScreen so text calls accumulate during the NVG frame and are flushed after endFrame.

  ---
  Option 3 — Reinitialize NVG context lazily (targets the likely root cause)

  The most probable issue: NVG's font atlas GL texture is created during NVGRenderer's static init, which runs before the GL context has the right state (Sodium modifies the pipeline early).

  In Vexel, defer nvgCreate to the first beginFrame call:

  // NVGRenderer static block: DON'T call nvgCreate here, just set vg = -1
  // In beginFrame():
  if (vg == -1L) {
      vg = NanoVGGL3.nvgCreate(3)
      if (vg == 0L) return  // failed
  }
  // then proceed normally

  This ensures nvgCreate (which calls glGenTextures for the font atlas) happens while a valid GL context is active and in the right state for rendering.

  ---
  Option 4 — Force stencil buffer off (quick experiment)

  If the issue is the stencil buffer not being available on your framebuffer attachment:

  nvgCreate(1)  // NVG_ANTIALIAS only, no NVG_STENCIL_STROKES

  NVG text doesn't use stencil, but stencil strokes do, and a missing stencil buffer can put NVG's GL state into a bad state that also breaks texturing.

  ---
