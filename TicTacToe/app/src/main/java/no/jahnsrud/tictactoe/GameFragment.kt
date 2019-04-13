package no.jahnsrud.tictactoe

import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_game.*
import no.jahnsrud.tictactoe.Models.Player

class GameFragment : Fragment(){

    val player1 = Player("Player 1", false)
    val player2 = Player("Player 2", true)

    var activePlayer = 1

    val PLAYER_1_SYMBOL = "X"
    val PLAYER_2_SYMBOL = "O"

    var gameButtons = arrayOf<Button>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onStart() {
        super.onStart()

        gameButtons = arrayOf(button1, button2, button3, button4, button5, button6, button7, button8, button9)
        gameButtons.forEach {
            it.setOnClickListener({ didInteractWithGameBoard(it as Button) })
        }

        resetButton.setOnClickListener({
            resetGame()
        })

        resetGame()

    }

    fun getIndexFromButton(button: Button) : Int {
        var index = 0

        when (button.id) {
            R.id.button1 -> index=1
            R.id.button2 -> index=2
            R.id.button3 -> index=3
            R.id.button4 -> index=4
            R.id.button5 -> index=5
            R.id.button6 -> index=6
            R.id.button7 -> index=7
            R.id.button8 -> index=8
            R.id.button9 -> index=9
        }

        return index

    }

    fun getButtonFromIndex(index: Int) : Button {

        var button:Button = Button(activity)

        when (index) {
            1 -> button=button1
            2 -> button=button2
            3 -> button=button3
            4 -> button=button4
            5 -> button=button5
            6 -> button=button6
            7 -> button=button7
            8 -> button=button8
            9 -> button=button9

        }

        return button;
    }

    fun didInteractWithGameBoard(selectedButton: Button) {

        val index = getIndexFromButton(selectedButton)

        if (canMakeMove(index)) {
            makeMove(selectedButton, index)

        } else {
            Toast.makeText(activity, "Move already made", Toast.LENGTH_SHORT).show()
        }

    }

    fun canMakeMove(index: Int) : Boolean {

        if (player1.moves.contains(index) || player2.moves.contains(index)) {
            return false
        }

        return true

    }

    private fun makeMove(button: Button, index: Int) {

        if (!canMakeMove(index)) {
            return
        }

        Log.d("Button.tag", ""+ button.tag)

        var symbol = ""

        if (activePlayer == 1) {
            player1.moves.add(index)
            symbol = PLAYER_1_SYMBOL
        } else {
            player2.moves.add(index)
            symbol = PLAYER_2_SYMBOL
        }

        button.setText(symbol)
        button.isEnabled = false

        checkWinner()

    }

    fun aiMakeMove() {

        // Veldig midlertidig!

        // Husk delay

        var random = (1..9).random()


        while (!canMakeMove(random)) {
            random = (0..8).random()

        }

        /*
          if (player1.moves.containsAll(listOf(1, 2))) {
            random = 3;
        }
         */

        makeMove(getButtonFromIndex(random), random)


    }


    fun checkWinner() {

        if (winningMoves.any{

                player1.moves.containsAll(it.asList())

            }) {
            Toast.makeText(activity, "Player 1 won 🥳", Toast.LENGTH_SHORT).show()
            endGame()


        } else if (winningMoves.any {
                player2.moves.containsAll(it.asList())

            }) {
            Toast.makeText(activity, "Player 2 won 🥳", Toast.LENGTH_SHORT).show()
            endGame()

        } else if (player1.moves.size + player2.moves.size == 9) {
            Toast.makeText(activity, "Hm! Draw!", Toast.LENGTH_SHORT).show()
            endGame()
        } else {
            setActivePlayer()
        }

    }

    fun endGame() {
        gameButtons.forEach {
            it.isEnabled = false

        }

        timer.stop()

    }

    /*
    Mulige vinnerkombinasjoner
    */

    val winningMoves: Array<IntArray> = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(1, 4, 7),
        intArrayOf(1, 5, 9),
        intArrayOf(2, 5, 8),
        intArrayOf(3, 6, 9),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 5, 3),
        intArrayOf(7, 8, 9)

    )



    fun setActivePlayer() {
        if (activePlayer == 1) {
            activePlayer = 2
            currentPlayerTextField.setText(player2.name)

            if (player2.isAI) {
                aiMakeMove()
            }

        } else {
            activePlayer = 1
            currentPlayerTextField.setText(player1.name)
        }


    }

    fun resetGame() {

        player1.clearMoves()
        player2.clearMoves()

        activePlayer = 1

        currentPlayerTextField.setText(player1.name)

        gameButtons.forEach {
            it.isEnabled = true
            it.text = "-"
        }

        timer.base = SystemClock.elapsedRealtime()
        timer.start()

    }


}
