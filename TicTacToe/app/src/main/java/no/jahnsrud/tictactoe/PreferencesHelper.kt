package no.jahnsrud.tictactoe

import android.content.Context
import android.content.SharedPreferences
import no.jahnsrud.tictactoe.Models.Player

object PreferencesHelper {

    val PREFS_FILENAME = "no.jahnsrud.tictactoe.prefs"
    var prefs: SharedPreferences? = null

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_FILENAME, 0)

    }

    fun savePlayer(player: Player, playerId:String) {
        val editor = prefs!!.edit()
        editor.putString("player_"+playerId, player.name)
        editor.apply()
    }

    fun loadPlayer(playerId:String) : String {
        val name = prefs!!.getString("player_"+playerId, "")
        return name
    }


}