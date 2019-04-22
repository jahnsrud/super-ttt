package no.jahnsrud.tictactoe


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 *
 */
class MainFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)

    }

    override fun onStart() {
        super.onStart()

        GameSettings.init(this.context!!)

        player1Box.setOnClickListener({
            openGame(true)
        })

        player2Box.setOnClickListener({
            openGame(false)
        })

        leaderboardButton.setOnClickListener({
            openLeaderboards()
        })

        leaderboardPipe.setOnClickListener({
            openLeaderboards()
        })
    }

    fun openLeaderboards() {
        Navigation.findNavController(this.view!!).navigate(R.id.action_mainFragment_to_leaderboardFragment)
        context?.let { SoundEffectPlayer.playWarp(it) }

    }

    fun openGame(playAgainstAi: Boolean) {

        GameSettings.setPlayMode(playAgainstAi)

        context?.let { it1 -> SoundEffectPlayer.playNextSound(it1) }
        Navigation.findNavController(this.view!!).navigate(R.id.action_mainFragment_to_startFragment)
    }


}
