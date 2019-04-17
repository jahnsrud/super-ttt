package no.jahnsrud.tictactoe

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_players.*
import no.jahnsrud.tictactoe.Models.Player
import android.view.inputmethod.InputMethodManager


class PlayersFragment : Fragment() {

    var player1 = Player()
    var player2 = Player()

    val DEFAULT_BOT_NAME = "TTTBot"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun onStart() {
        super.onStart()

        setOnClickListeners()
        loadPlayers()
        updateUI()

    }

    fun setOnClickListeners() {
        playButton.setOnClickListener({
            startGame()
        })

        aiSwitch.setOnCheckedChangeListener({ buttonView, isChecked ->

            // player2.name = ""
            player2.isAI = isChecked
            // player2TextField.text.clear()
            GameSettings.setPlayMode(isChecked)

            updateUI()
            savePlayers()
            loadPlayers()

        })
    }

    fun loadPlayers() {
        player1 = GameSettings.loadPlayer("1")
        player2 = GameSettings.loadPlayer("2")

        player1TextField.setText(player1.name)

        player2.isAI = GameSettings.checkIsAi()

        updateUI()


    }

    fun updateUI() {
        if (player2.isAI) {
            // player2TextField.text.clear()
            player2TextField.visibility = View.GONE
            player2TextField.setText(DEFAULT_BOT_NAME)

        } else {
            player2TextField.visibility = View.VISIBLE
            player2TextField.setText(player2.name)
        }

        aiSwitch.isChecked = player2.isAI
        player2TextField.isEnabled = !player2.isAI
    }

    fun startGame() {

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

        savePlayers()

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


    fun savePlayers() {

        var player1Name:String = player1TextField.text.toString().trimEnd()
        var player2Name:String = player2TextField.text.toString().trimEnd()

        player1.name = player1Name
        player2.name = player2Name

        GameSettings.savePlayer(player1, "1")
        GameSettings.savePlayer(player2, "2")


    }



}
