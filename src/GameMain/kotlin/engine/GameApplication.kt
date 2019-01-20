package engine

import engine.io.printErr
import engine.logic.BaseGameLogic
import engine.time.Timer
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.reinterpret
import sdl.*

abstract class GameApplication {

    private var window: kotlinx.cinterop.CPointer<cnames.structs.SDL_Window>? = null
    private var renderer: kotlinx.cinterop.CPointer<cnames.structs.SDL_Renderer>? = null
    private var isQuitting = false

    private var gameLogic: BaseGameLogic? = null

    abstract val gameTitle: String

    abstract fun createGameAndView(): BaseGameLogic

    fun initInstance(screenWidth: Int = 640, screenHeight: Int = 480): Boolean {
        if (SDL_Init(SDL_INIT_VIDEO) < 0) {
            printErr("could not initialize sdl2: ${SDL_GetError()}")
            return false
        }

        window = SDL_CreateWindow(gameTitle, 100, 100, screenWidth, screenHeight, SDL_WINDOW_OPENGL)
        if (window == null) {
            printErr("could not initialize sdl2: ${SDL_GetError()}")
            return false
        }

        renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_ACCELERATED)
        if (renderer == null) {
            printErr("Could not create a renderer: ${SDL_GetError()}")
            return false
        }

        gameLogic = createGameAndView()

        return true
    }

    fun shutDown() {
        SDL_DestroyRenderer(renderer)
        SDL_DestroyWindow(window)
        SDL_Quit()
    }

    fun run() {
        val minimumFrameTime = 1000.toUInt() / FPS.toUInt()
        val timer = Timer()
        var isRunning = true

        while (isRunning) {

            val frameTime = timer.getTicks
            if (isQuitting) {
                isRunning = false
            }

            memScoped {
                val event = alloc<SDL_Event>()
                if (SDL_PollEvent(event.ptr.reinterpret()) != 0) {
                    when (event.type) {
                        SDL_QUIT -> isQuitting = true
                    }
                }
            }

            val timeBetweenLastFrameAndCurrentFrame = timer.getTicks - frameTime
            onRender(renderer)
            if (timeBetweenLastFrameAndCurrentFrame < minimumFrameTime) {
                SDL_Delay(minimumFrameTime - timeBetweenLastFrameAndCurrentFrame)
            }
        }
    }

    private fun onRender(renderer: kotlinx.cinterop.CPointer<cnames.structs.SDL_Renderer>?) {
        gameLogic?.gameViews?.forEach { it.onRender(renderer) }
    }
}
