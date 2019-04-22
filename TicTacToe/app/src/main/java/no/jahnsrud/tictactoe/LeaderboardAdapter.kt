package no.jahnsrud.tictactoe

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.leaderboard_list_item.view.*
import no.jahnsrud.tictactoe.Models.Player

class LeaderboardAdapter internal constructor(
    context: Context
) : androidx.recyclerview.widget.RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var scores = GameSettings.getHighscores()

    inner class LeaderboardViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val itemView = inflater.inflate(no.jahnsrud.tictactoe.R.layout.leaderboard_list_item, parent, false)
        return LeaderboardViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {

        // val current = scores.get(position)

        val keyList = ArrayList(scores.keys)
        val valueList = ArrayList(scores.values)

        for (score in scores) {
            holder.itemView.playerNameField.text = keyList[position]
            holder.itemView.playerScoreField.text = valueList.get(position).toString()
            holder.itemView.setOnClickListener() {


            }

        }

    }

    override fun getItemCount() = scores.size
}