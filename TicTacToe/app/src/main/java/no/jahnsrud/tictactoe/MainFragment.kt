package no.jahnsrud.tictactoe


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 *
 */
class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_main, container, false)

    }

    override fun onStart() {
        super.onStart()

        player1Box.setOnClickListener({
            openGame()
        })

        player2Box.setOnClickListener({
            openGame()
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

    fun openGame() {
        context?.let { it1 -> SoundEffectPlayer.playNextSound(it1) }
        Navigation.findNavController(this.view!!).navigate(R.id.action_mainFragment_to_startFragment)
    }


}
