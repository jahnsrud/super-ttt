package no.jahnsrud.tictactoe

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import no.jahnsrud.tictactoe.Models.Player

object PreferencesHelper {

    val PREFS_FILENAME = "no.jahnsrud.tictactoe.prefs"
    var prefs: SharedPreferences? = null

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_FILENAME, 0)

    }

    fun savePlayer(player: Player, playerId:String) {

        val gson = Gson()
        val playerString = gson.toJson(player)

        val editor = prefs!!.edit()
        editor.putString("player_"+playerId, playerString)
        editor.apply()
    }

    fun loadPlayer(playerId:String) : Player {
        val playerString = prefs!!.getString("player_"+playerId, "")
        val gson = Gson()

        val player = gson.fromJson(playerString, Player::class.java)

        if (player != null) {
            return player
        } else {
            return Player("", false)
        }

    }

    fun addVictoryToHighscore(player: Player) {

        val playerName = player.name.toUpperCase()
        var score = 1

        val highscores = HashMap<String, Int>()

        if (highscores.contains(playerName)) {
            score = highscores.get(playerName)!! + 1
        }



        // logic...

    }

    fun getHighscores() : HashMap<String, Int> {

        // TODO!

        return HashMap<String, Int>()
    }

}