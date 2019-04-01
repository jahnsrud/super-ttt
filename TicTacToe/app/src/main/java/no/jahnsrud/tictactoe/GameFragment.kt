package no.jahnsrud.tictactoe


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : View.OnClickListener, Fragment(){

    var player1Moves = intArrayOf()
    var player2Moves = intArrayOf()
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

        onClick(button1)
    }

    override fun onClick(v: View?) {

        print("ID: " + v?.tag)

        when (v?.id) {


        }

    }

    fun didMakeMove(view: View) {
        print("HEI")
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




}
