package no.jahnsrud.tictactoe

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_start.*
import no.jahnsrud.tictactoe.Models.Player
import android.widget.CompoundButton


class StartFragment : Fragment() {

    var shouldPlayAgainAi:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onStart() {
        super.onStart()

        playButton.setOnClickListener({
            savePlayers()
            Navigation.findNavController(this.view!!).navigate(R.id.gameFragment)
        })

        aiSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->

            shouldPlayAgainAi = isChecked
            loadPlayers()

        })

        PreferencesHelper.init(this.context!!)

        loadPlayers()

    }

    override fun onResume() {
        super.onResume()
        loadPlayers()

    }

    fun loadPlayers() {
        val player1 = PreferencesHelper.loadPlayer("1")
        player1TextField.setText(player1)

        // TODO: Refaktorer slik at onResume ikke overskriver
        if (shouldPlayAgainAi) {
            player2TextField.setText("TTTBot")
        } else {
            val player2 = PreferencesHelper.loadPlayer("2")
            player2TextField.setText(player2)
        }

        player2TextField.isEnabled = !shouldPlayAgainAi

    }

    fun savePlayers() {

        var player1Name:String = player1TextField.text.toString().trimEnd()
        var player2Name:String = player2TextField.text.toString().trimEnd()

        val player1 = Player(player1Name, false)
        val player2 = Player(player2Name, false)

        PreferencesHelper.savePlayer(player1, "1")
        PreferencesHelper.savePlayer(player2, "2")


    }



}
