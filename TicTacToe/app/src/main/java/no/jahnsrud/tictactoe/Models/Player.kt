package no.jahnsrud.tictactoe.Models

class Player {
    var name: String = ""
    var moves: ArrayList<Int> = ArrayList<Int>()
    var isAI: Boolean = false

    constructor(name:String, isAi: Boolean) {
        this.name = name
        this.isAI = isAi
    }

    fun clearMoves() {
        moves.clear()
    }

}