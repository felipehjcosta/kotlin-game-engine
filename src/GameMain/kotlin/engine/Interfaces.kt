package engine

interface GameLogic {
    fun onUpdate()
}

interface GameView {
    fun onRender(renderer: kotlinx.cinterop.CPointer<cnames.structs.SDL_Renderer>?)
}

typealias GameViewList = MutableList<GameView>
