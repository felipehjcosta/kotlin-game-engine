package engine

interface GameLogic {
    fun onUpdate()
}

interface GameView {
    fun onRender(renderer: kotlinx.cinterop.CPointer<cnames.structs.SDL_Renderer>?, elapsedTime: UInt)
}

typealias GameViewList = MutableList<GameView>
