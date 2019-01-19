package engine

import engine.io.printErr
import kotlinx.cinterop.pointed
import sdl.*

class GameApplication {

    private var window: kotlinx.cinterop.CPointer<cnames.structs.SDL_Window>? = null
    private var windowSurface: kotlinx.cinterop.CPointer<sdl.SDL_Surface>? = null

    fun initInstance(): Boolean {
        if (SDL_Init(SDL_INIT_VIDEO) < 0) {
            printErr("could not initialize sdl2: ${SDL_GetError()}")
            return false
        }

        window = SDL_CreateWindow("Hello World!", 100, 100, 640, 480, SDL_WINDOW_SHOWN)
        if (window == null) {
            printErr("could not initialize sdl2: ${SDL_GetError()}")
            return false
        }

        windowSurface = SDL_GetWindowSurface(window)

        return true
    }

    fun shutDown() {
        SDL_DestroyWindow(window)
        SDL_Quit()
    }

    fun run() {
        SDL_FillRect(windowSurface, null, SDL_MapRGB(windowSurface?.pointed?.format, 0xFF, 0xFF, 0xFF))
        SDL_UpdateWindowSurface(window)
        SDL_Delay(4000.toUInt())
    }
}