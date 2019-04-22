package no.jahnsrud.tictactoe


import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_leaderboard.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView


class LeaderboardFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        leaderboardsRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(ctx)

        leaderboardsRecyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                context!!,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )

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
