package no.jahnsrud.tictactoe.models

import java.io.Serializable

open class Player : Serializable {
    var name: String = ""
    var moves: ArrayList<Int> = ArrayList<Int>()
    var isAI: Boolean = false

    constructor(name:String, isAi: Boolean) {
        this.name = name
        this.isAI = isAi
    }

    constructor() {
        this.name = ""
        this.isAI = false
    }

    fun clearMoves() {
        moves.clear()
    }

}