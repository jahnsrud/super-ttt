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

class GameFragment : View.OnClickListener, Fragment(){

    var player1Moves = ArrayList<Int>()
    var player2Moves = ArrayList<Int>()
    var activePlayer = 1
    var isAIEnabled = false

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
            reset()
        })

        terribleSetOnClickListener()

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

        if (player1Moves.contains(cellId) || player2Moves.contains(cellId)) {
            Toast.makeText(activity, "Move already made", Toast.LENGTH_SHORT).show()
            return
        }

        if (activePlayer == 1) {
            player1Moves.add(cellId)
        } else {
            player2Moves.add(cellId)
        }


        var symbol = ""

        if (activePlayer == 1) {
            symbol = "X"
        } else {
            symbol = "O"
        }

        button.setText(symbol)
        button.isEnabled = false

        setActivePlayer()

        ////////

        // checkWinner()

        val test = ArrayList<Int>()
        test.add(1)
        test.add(2)
        test.add(3)

        if (player1Moves.containsAll(test)) {
            Toast.makeText(activity, "Winner Found!", Toast.LENGTH_SHORT).show()

        }



        ////////

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
        } else {
            activePlayer = 1
        }
    }

    override fun onClick(v: View?) {

        print("ID: " + v?.tag)

        when (v?.id) {


        }

    }

    fun reset() {

        player1Moves.clear()
        player2Moves.clear()

        activePlayer = 1

        // TODO: Reset all buttons



        Toast.makeText(activity, "Kommer snart", Toast.LENGTH_SHORT).show()
    }




    private fun terribleSetOnClickListener() {
        button1.setOnClickListener({
            didMakeMove(button1)
        })

        button2.setOnClickListener({
            didMakeMove(button2)
        })

        button3.setOnClickListener({
            didMakeMove(button3)
        })

        button4.setOnClickListener({
            didMakeMove(button4)
        })

        button5.setOnClickListener({
            didMakeMove(button5)
        })

        button6.setOnClickListener({
            didMakeMove(button6)
        })

        button7.setOnClickListener({
            didMakeMove(button7)
        })

        button8.setOnClickListener({
            didMakeMove(button8)
        })

        button9.setOnClickListener({
            didMakeMove(button9)
        })
    }


}
