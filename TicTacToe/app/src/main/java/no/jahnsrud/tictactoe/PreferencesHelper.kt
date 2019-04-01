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

    fun savePlayer(player: Player) {
        val editor = prefs!!.edit()
        editor.putString("name", player.name)
        editor.apply()
    }

    fun loadLastPlayer() : String {
        val name = prefs!!.getString("name", "")
        return name
    }


}