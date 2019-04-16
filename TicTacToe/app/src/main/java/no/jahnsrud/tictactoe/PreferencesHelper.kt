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

    fun setPlayMode(isAi: Boolean) {
        val editor = prefs!!.edit()
        editor.putBoolean("isAi", isAi)
        editor.apply()
    }

    fun checkIsAi() : Boolean {

        return prefs!!.getBoolean("isAi", false)
    }

    fun savePlayer(player: Player, playerId:String) {

        val gson = Gson()
        val playerString = gson.toJson(player)

        val editor = prefs!!.edit()
        editor.putString("player_"+playerId, playerString)
        editor.apply()
    }

    fun loadPlayer(playerId:String) : Player {

        val playerPreferencesId = "player_"+playerId

        if (prefs!!.contains(playerPreferencesId)) {

            val playerString = prefs!!.getString(playerPreferencesId, "")

            if (playerString.length > 0) {
                val gson = Gson()
                val player = gson.fromJson(playerString, Player::class.java)


                if (player != null) {
                    return player

                } else {
                    return Player("", false)

                }
            }
        }

        return Player("", false)
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