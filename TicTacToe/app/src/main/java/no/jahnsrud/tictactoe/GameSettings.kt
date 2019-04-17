package no.jahnsrud.tictactoe

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import no.jahnsrud.tictactoe.Models.Player
import com.google.gson.reflect.TypeToken



object GameSettings {

    val PREFS_FILENAME = "no.jahnsrud.tictactoe.prefs"
    var prefs: SharedPreferences? = null
    val PLAYER_PREFIX = "player_"
    val HIGHSCORES_KEY = "highscores"

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_FILENAME, 0)

    }

    fun setPlayMode(isAi: Boolean) {
        val editor = prefs!!.edit()
        editor.putBoolean("shouldPlayAgainstAi", isAi)
        editor.apply()
    }

    fun checkIsAi() : Boolean {
        return prefs!!.getBoolean("shouldPlayAgainstAi", false)
    }

    fun savePlayer(player: Player, playerId:String) {

        val gson = Gson()
        val playerString = gson.toJson(player)

        val editor = prefs!!.edit()
        editor.putString(PLAYER_PREFIX+playerId, playerString)
        editor.apply()
    }

    fun loadPlayer(playerId:String) : Player {

        val playerPreferencesId = PLAYER_PREFIX+playerId

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
        var score = 0

        val highscores = getHighscores()

        if (highscores.contains(playerName)) {
            score = highscores.get(playerName)!! + 1
        } else {
            // Welcome, new player!
            score = 1
        }

        highscores.put(playerName, score)

        // Convert
        val gson = Gson()
        val hashMapString = gson.toJson(highscores)

        // Save
        val editor = prefs!!.edit()
        editor.putString(HIGHSCORES_KEY, hashMapString)
        editor.apply()



    }

    fun getHighscores() : HashMap<String, Int> {

        val gson = Gson()

        val storedHashMapString = prefs!!.getString(HIGHSCORES_KEY, "")
        if (storedHashMapString.length > 0) {
            val type = object : TypeToken<HashMap<String, Int>>() {

            }.type
            val testHashMap2:HashMap<String, Int> = gson.fromJson(storedHashMapString, type)

            testHashMap2.forEach { (key, value) -> println("$key = $value") }


            return testHashMap2
        }

        return HashMap<String, Int>()
    }

}