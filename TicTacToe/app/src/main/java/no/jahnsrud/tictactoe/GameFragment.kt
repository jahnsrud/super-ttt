package no.jahnsrud.tictactoe

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*
import no.jahnsrud.tictactoe.GameLogic.TicTacToeGame
import no.jahnsrud.tictactoe.Models.Player

class GameFragment : Fragment(){

    val game = TicTacToeGame()

    val player1 = PreferencesHelper.loadPlayer("1")
    val player2 = PreferencesHelper.loadPlayer("2")
    var activePlayer = 1

    val PLAYER_1_SYMBOL = "X"
    val PLAYER_2_SYMBOL = "O"
    val DELAY_IN_MS:Long = 190

    var gameButtons = arrayOf<Button>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onStart() {
        super.onStart()

        gameButtons = arrayOf(button1, button2, button3, button4, button5, button6, button7, button8, button9)
        gameButtons.forEach {
            it.setOnClickListener{ didInteractWithGameBoard(it as Button) }
        }

        resetButton.setOnClickListener({
            resetGame()
            context?.let { it1 -> SoundEffectPlayer.playPause(it1) }
        })

        endGameButton.setOnClickListener({
            Navigation.findNavController(this.view!!).popBackStack()
            context?.let { it1 -> SoundEffectPlayer.playPause(it1) }

        })

        resetGame()

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


        val handler = Handler()
        handler.postDelayed({

            // Veldig midlertidig!
            var random = getRandomIndex()

            while (!canMakeMove(random)) {
                random = getRandomIndex()

            }

            /*
              if (player1.moves.containsAll(listOf(1, 2))) {
                random = 3;
            }
             */

            makeMove(getButtonFromIndex(random), random)

        }, DELAY_IN_MS)



    }

    fun getRandomIndex() : Int {
        return (1..9).random()
    }


    fun checkWinner() {

        if (winningMoves.any{

                player1.moves.containsAll(it.asList())

            }) {
            Toast.makeText(activity, player1.name + " won 🥳", Toast.LENGTH_SHORT).show()
            context?.let { SoundEffectPlayer.playWin(it) }

            endGame()


        } else if (winningMoves.any {
                player2.moves.containsAll(it.asList())

            }) {
            Toast.makeText(activity, player2.name + " won 🥳", Toast.LENGTH_SHORT).show()
            context?.let { SoundEffectPlayer.playLost(it) }

            endGame()

        } else if (player1.moves.size + player2.moves.size == 9) {
            Toast.makeText(activity, "Hm! Draw!", Toast.LENGTH_SHORT).show()
            context?.let { SoundEffectPlayer.playLost(it) }

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

    @SuppressLint("ResourceAsColor")
    fun setActivePlayer() {
        if (activePlayer == 1) {
            activePlayer = 2

            player1Name.setTextColor(R.color.inactivePlayer)
            player2Name.setTextColor(R.color.activePlayer)

            player1Image.setImageResource(R.drawable.mario_unselected)
            player2Image.setImageResource(R.drawable.luigi_selected)

            if (player2.isAI) {
                aiMakeMove()
            }

        } else {
            activePlayer = 1

            player1Name.setTextColor(R.color.activePlayer)
            player2Name.setTextColor(R.color.inactivePlayer)

            player1Image.setImageResource(R.drawable.mario_selected)
            player2Image.setImageResource(R.drawable.luigi_unselected)
        }


    }

    fun resetGame() {

        player1.clearMoves()
        player2.clearMoves()

        activePlayer = 1

        gameButtons.forEach {
            it.isEnabled = true
            it.text = ""
        }

        player1Name.text = player1.name
        player2Name.text = player2.name

        timer.base = SystemClock.elapsedRealtime()
        timer.start()

    }

    /**
     * Mulige vinnerkombinasjoner
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

    /**
     *
     * Helper methods
     * First: getting the user selected button and convert it to a index
     * Second: converts an index to a button (useful when the bot makes a move)
     *
     */

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

}
