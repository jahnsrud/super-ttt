package no.jahnsrud.tictactoe


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_leaderboard.*

class LeaderboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leaderboard, container, false)
    }

    override fun onStart() {
        super.onStart()

        setOnClickListeners()
        configureRecyclerView()

    }

    fun configureRecyclerView() {

        val ctx = context ?: return

        val adapter = LeaderboardAdapter(ctx)
        leaderboardsRecyclerView.adapter = adapter
        leaderboardsRecyclerView.layoutManager = LinearLayoutManager(ctx)

    }

    fun setOnClickListeners() {
        backButtonPipe.setOnClickListener({
            goBack()
        })

        backButtonText.setOnClickListener({
            goBack()
        })
    }

    fun goBack() {
        context?.let { SoundEffectPlayer.playWarp(it) }
        Navigation.findNavController(this.view!!).popBackStack()

    }


}
