package game

fun main(args: Array<String>) {
    println("Hello, Kotlin/Native!")

    val game = DriftsGameApplication()

    if (!game.initInstance()) {
        game.shutDown()
        return
    }

    game.run()

    game.shutDown()
}
