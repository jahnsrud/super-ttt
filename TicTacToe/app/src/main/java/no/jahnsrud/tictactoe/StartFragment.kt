package no.jahnsrud.tictactoe

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_start.*
import no.jahnsrud.tictactoe.Models.Player


class StartFragment : Fragment() {

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
            savePlayer()
            Navigation.findNavController(this.view!!).navigate(R.id.gameFragment)
        })

        PreferencesHelper.init(this.context!!)

        loadPlayer()

    }

    fun loadPlayer() {
        val name = PreferencesHelper.loadLastPlayer()
        player1TextField.setText(name)

    }

    fun savePlayer() {
        val player = Player(player1TextField.text.toString(), ArrayList<Int>(), false)
        PreferencesHelper.savePlayer(player)

    }



}
