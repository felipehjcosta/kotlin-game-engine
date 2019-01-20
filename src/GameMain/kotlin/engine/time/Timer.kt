package engine.time

import sdl.SDL_GetTicks

class Timer {

    var lastTickTime: UInt = 0.toUInt()
    var delta: UInt = 0.toUInt()

    fun tick() {
        val tickTime = SDL_GetTicks()
        delta = tickTime - lastTickTime
        lastTickTime = tickTime
    }
}
