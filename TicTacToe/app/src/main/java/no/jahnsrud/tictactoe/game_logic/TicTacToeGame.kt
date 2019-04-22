package no.jahnsrud.tictactoe.game_logic

import no.jahnsrud.tictactoe.models.Player

class TicTacToeGame {
    var player1 = Player()
    var player2 = Player()

    constructor(player1: Player, player2: Player) {
        this.player1 = player1
        this.player2 = player2
    }

    fun canMakeMove(index: Int) : Boolean {

        if (player1.moves.contains(index) || player2.moves.contains(index)) {
            return false
        }

        return true

    }

}