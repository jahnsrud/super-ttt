package no.jahnsrud.tictactoe

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.leaderboard_list_item.view.*
import no.jahnsrud.tictactoe.Models.Player

class LeaderboardAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var scores = arrayListOf<String>("Nintendo", "IKEA", "Apple", "Vy", "Flytoget", "Ruter")

    inner class LeaderboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val itemView = inflater.inflate(no.jahnsrud.tictactoe.R.layout.leaderboard_list_item, parent, false)
        return LeaderboardViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        val current = scores[position]

        holder.itemView.playerNameField.text = current
        holder.itemView.playerScoreField.text = "0000?!"
        holder.itemView.setOnClickListener() {

        }

    }

    override fun getItemCount() = scores.size
}