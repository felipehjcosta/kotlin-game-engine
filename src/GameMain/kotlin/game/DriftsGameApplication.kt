package game

import engine.GameApplication
import engine.logic.BaseGameLogic
import game.logic.DriftsGameLogic
import game.view.ColorGameView
import game.view.ImageGameView

class DriftsGameApplication : GameApplication() {

    override val gameTitle: String
        get() = "Drifts Game"

    override fun createGameAndView(): BaseGameLogic {
        return DriftsGameLogic().apply {
            addGameView(ImageGameView())
        }
    }
}
