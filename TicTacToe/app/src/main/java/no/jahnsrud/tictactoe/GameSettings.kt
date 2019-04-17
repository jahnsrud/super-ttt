package no.jahnsrud.tictactoe

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import no.jahnsrud.tictactoe.Models.Player

object GameSettings {

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
            return convertStringToPlayer(prefs!!.getString(playerPreferencesId, ""))
        }

        return Player()
    }

    fun convertStringToPlayer(playerString: String) : Player {
        if (playerString.length > 0) {
            val gson = Gson()
            val player = gson.fromJson(playerString, Player::class.java)

            if (player != null) {
                return player

            }
        }

        return Player()
    }

    fun addVictoryToHighscore(player: Player) {

        val playerName = player.name.toUpperCase()
        var score = 1

        val highscores = getHighscores()

        if (highscores.contains(playerName)) {
            score = highscores.get(playerName)!! + 1
        } else {
            highscores.put(playerName, score)
        }

        // Save...

        val editor = prefs!!.edit()
        // editor.put
        editor.apply()



    }

    fun getHighscores() : HashMap<String, Int> {

        // TODO!

        return HashMap<String, Int>()
    }

}