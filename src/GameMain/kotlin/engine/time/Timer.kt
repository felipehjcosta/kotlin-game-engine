package engine.time

import sdl.SDL_GetTicks
import sdl.Uint32

class Timer {

    val getTicks: Uint32
        get() {
            return SDL_GetTicks()
        }

}