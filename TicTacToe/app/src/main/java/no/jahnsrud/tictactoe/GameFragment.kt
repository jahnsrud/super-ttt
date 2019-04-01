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



    fun didMakeMove(button: Button) {

        // Log.d("Button", ""+ button.id)

        // TODO: FIX!!!

        Log.d("Button", ""+ button.id)

        if (player1Moves.contains(button.id as Int) || player2Moves.contains(button.id as Int)) {
            Toast.makeText(activity, "Move already made", Toast.LENGTH_SHORT).show()
            return
        }

        if (activePlayer == 1) {
            player1Moves.add(button.id as Int)
        } else {
            player2Moves.add(button.id as Int)
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

    }

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
        Toast.makeText(activity, "Kommer snart", Toast.LENGTH_SHORT).show()
    }

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
