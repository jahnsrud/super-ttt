package no.jahnsrud.tictactoe

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_players.*
import no.jahnsrud.tictactoe.Models.Player
import android.view.inputmethod.InputMethodManager


class PlayersFragment : Fragment() {

    var player1 = Player("", false)
    var player2 = Player("", false)

    val DEFAULT_BOT_NAME = "TTTBot"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun onStart() {
        super.onStart()

        playButton.setOnClickListener({
            startGame()
        })

        aiSwitch.setOnCheckedChangeListener({ buttonView, isChecked ->

            player2.isAI = isChecked

            if (!player2.isAI) {
                player2TextField.text.clear()
            }

            savePlayers()
            loadPlayers()

        })

        PreferencesHelper.init(this.context!!)

        loadPlayers()

    }

    fun startGame() {
        savePlayers()

        if (player1.name.length < 1) {
            player1TextField.setFocusableInTouchMode(true);
            player1TextField.requestFocus()
            // openKeyboard()

            return
        } else if (player2.name.length < 1) {
            player2TextField.setFocusableInTouchMode(true);
            player2TextField.requestFocus()
            // openKeyboard()
            return
        }

        this.context?.let { it1 -> SoundEffectPlayer.playNextSound(it1) }
        Navigation.findNavController(this.view!!).navigate(R.id.action_startFragment_to_gameFragment)
    }

    @SuppressLint("ServiceCast")
    fun openKeyboard() {
        val inputMethodManager = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    override fun onResume() {
        super.onResume()
        loadPlayers()

    }

    fun loadPlayers() {
        player1 = PreferencesHelper.loadPlayer("1")
        player1TextField.setText(player1.name)

        player2 = PreferencesHelper.loadPlayer("2")

        if (player2.isAI) {
            player2TextField.setText(DEFAULT_BOT_NAME)
        } else {
            player2TextField.setText(player2.name)
        }

        aiSwitch.isChecked = player2.isAI
        player2TextField.isEnabled = !player2.isAI

    }

    fun savePlayers() {

        var player1Name:String = player1TextField.text.toString().trimEnd()
        var player2Name:String = player2TextField.text.toString().trimEnd()

        player1.name = player1Name
        player2.name = player2Name

        PreferencesHelper.savePlayer(player1, "1")
        PreferencesHelper.savePlayer(player2, "2")


    }



}
