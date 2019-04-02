package no.jahnsrud.tictactoe


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_game.*
import no.jahnsrud.tictactoe.Models.Player

class GameFragment : View.OnClickListener, Fragment(){

    var player1 = Player("Player 1", false)
    var player2 = Player("Player 2", false)

    var activePlayer = 1

    var isAIEnabled = false

    val gameButtons = arrayOf(button1, button2, button3, button4, button5, button6, button7, button8, button9)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onStart() {
        super.onStart()

        resetButton.setOnClickListener({
            resetGame()
        })

        gameButtons.forEach {
            it.setOnClickListener({ didMakeMove(it as Button) })
        }

        resetGame()

    }



    fun didMakeMove(selectedButton: Button) {

        var cellId = 0

        when (selectedButton.id) {
            R.id.button1 -> cellId=1
            R.id.button2 -> cellId=2
            R.id.button3 -> cellId=3
            R.id.button4 -> cellId=4
            R.id.button5 -> cellId=5
            R.id.button6 -> cellId=6
            R.id.button7 -> cellId=7
            R.id.button8 -> cellId=8
            R.id.button9 -> cellId=9
        }

        performGameLogic(selectedButton, cellId)

    }

    private fun performGameLogic(button: Button, cellId: Int) {

        // TODO: FIX!!!

        Log.d("Button.id", ""+ button.id)
        Log.d("Button.tag", ""+ button.tag)

        if (player1.moves.contains(cellId) || player2.moves.contains(cellId)) {
            Toast.makeText(activity, "Move already made", Toast.LENGTH_SHORT).show()
            return
        }

        if (activePlayer == 1) {
            player1.moves.add(cellId)
        } else {
            player2.moves.add(cellId)
        }


        var symbol = ""

        if (activePlayer == 1) {
            symbol = "X"
        } else {
            symbol = "O"
        }

        button.setText(symbol)
        button.isEnabled = false

        checkWinner()

    }

    fun checkWinner() {
        val test = ArrayList<Int>()
        test.add(1)
        test.add(2)
        test.add(3)

        /*
        for (i in winningMoves) {
            for (j in winningMoves[i]) {



            }
        }*/

        if (player1.moves.containsAll(test)) {
            Toast.makeText(activity, "Player 1 won 🥳", Toast.LENGTH_SHORT).show()
        } else if (player2.moves.containsAll(test)) {
            Toast.makeText(activity, "Player 2 won 🥳", Toast.LENGTH_SHORT).show()
        }

        if (player1.moves.size + player2.moves.size == 9) {
            Toast.makeText(activity, "Hm! Draw!", Toast.LENGTH_SHORT).show()
            return
        }

        setActivePlayer()

    }


    val winningMoves: Array<IntArray> = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(1, 4, 7),
        intArrayOf(1, 5, 9),
        intArrayOf(3, 6, 9),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 5, 3),
        intArrayOf(7, 8, 9)

    )
    /*

    Mulige vinnerkombinasjoner

    1, 2, 3
    1, 4, 7
    1, 5, 9
    3, 6, 9
    4, 5, 6
    7, 5, 3
    7, 8, 9

     */


    fun setActivePlayer() {
        if (activePlayer == 1) {
            activePlayer = 2
            currentPlayerTextField.setText(player2.name)
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
            it.text = "/"
        }

    }


}
