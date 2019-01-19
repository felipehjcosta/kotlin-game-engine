package game

import engine.GameApplication

fun main(args: Array<String>) {
    println("Hello, Kotlin/Native!")

    val game = GameApplication()

    if (!game.initInstance()) {
        game.shutDown()
        return
    }

    game.run()

    game.shutDown()
}
