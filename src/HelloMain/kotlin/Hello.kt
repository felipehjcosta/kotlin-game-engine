package sample.hello

import kotlinx.cinterop.pointed
import platform.posix.fflush
import platform.posix.fprintf
import sdl.*
import kotlin.system.exitProcess

val STDERR = platform.posix.fdopen(2, "w")
fun printErr(message: String) {
    fprintf(STDERR, message + "\n")
    fflush(STDERR)
}

fun main(args: Array<String>) {
    println("Hello, Kotlin/Native!")
    if (SDL_Init(SDL_INIT_VIDEO) < 0) {
        printErr("could not initialize sdl2: ${SDL_GetError()}")
        exitProcess(1)
    }

    val window = SDL_CreateWindow("Hello World!", 100, 100, 640, 480, SDL_WINDOW_SHOWN)
    if (window == null) {
        printErr("could not initialize sdl2: ${SDL_GetError()}")
        SDL_Quit()
        exitProcess(1)
    }

    val screenSurface = SDL_GetWindowSurface(window)

    SDL_FillRect(screenSurface, null, SDL_MapRGB(screenSurface?.pointed?.format, 0xFF, 0xFF, 0xFF))
    SDL_UpdateWindowSurface(window)
    SDL_Delay(2000)
    SDL_DestroyWindow(window)
    SDL_Quit()
}
