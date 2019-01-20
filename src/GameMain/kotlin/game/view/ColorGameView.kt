package game.view

import cnames.structs.SDL_Renderer
import engine.GameView
import kotlinx.cinterop.CPointer
import sdl.SDL_RenderClear
import sdl.SDL_RenderPresent
import sdl.SDL_SetRenderDrawColor
import kotlin.random.Random

class ColorGameView : GameView {

    override fun onRender(renderer: CPointer<SDL_Renderer>?) {
        val red = (Random.nextInt() % 255).toUByte()
        val green = (Random.nextInt() % 255).toUByte()
        val blue = (Random.nextInt() % 255).toUByte()

        SDL_SetRenderDrawColor(renderer, red, green, blue, 255.toUByte())
        SDL_RenderClear(renderer)
        SDL_RenderPresent(renderer)
    }
}