package engine.logic

import engine.GameLogic
import engine.GameView
import engine.GameViewList

abstract class BaseGameLogic : GameLogic {

    val gameViews: GameViewList = mutableListOf()

    fun addGameView(view: GameView) {
        gameViews.add(view)
    }

    override fun onUpdate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}