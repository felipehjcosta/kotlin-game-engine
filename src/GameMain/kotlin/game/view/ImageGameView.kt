package game.view

import cnames.structs.SDL_Renderer
import engine.GameView
import kotlinx.cinterop.*
import sdl_image.*

class ImageGameView : GameView {


    override fun onRender(renderer: CPointer<SDL_Renderer>?, elapsedTime: UInt) = memScoped {

        val texture = IMG_LoadTexture(renderer, "bubbles.png")

        val textureWidth = alloc<IntVar>()
        val textureHeight = alloc<IntVar>()
        SDL_QueryTexture(texture, null, null, textureWidth.ptr, textureHeight.ptr)

        val rect = alloc<SDL_Rect>().apply {
            x = 640 / 2
            y = 480 / 2
            w = textureWidth.value
            h = textureHeight.value
        }

        SDL_SetRenderDrawColor(renderer, 255.toUByte(), 255.toUByte(), 255.toUByte(), 0.toUByte())
        SDL_RenderClear(renderer);
        SDL_RenderCopy(renderer, texture, null, rect.ptr)
        SDL_RenderPresent(renderer)

        SDL_DestroyTexture(texture)
    }
}
