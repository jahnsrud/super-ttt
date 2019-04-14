package no.jahnsrud.tictactoe

import android.R
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.content.res.AssetFileDescriptor



object SoundEffectPlayer : MediaPlayer() {

    init {

    }

    fun playNextSound(context: Context) {
        playSoundWithId(context, "android.resource://no.jahnsrud.tictactoe/raw/sound_next")

    }

    fun playLost(context: Context) {
        playSoundWithId(context, "android.resource://no.jahnsrud.tictactoe/raw/sound_lost")

    }

    fun playPause(context: Context) {
        playSoundWithId(context, "android.resource://no.jahnsrud.tictactoe/raw/sound_pause")

    }

    fun playWarp(context: Context) {
        playSoundWithId(context, "android.resource://no.jahnsrud.tictactoe/raw/sound_warp")

    }

    fun playWin(context: Context) {
        playSoundWithId(context, "android.resource://no.jahnsrud.tictactoe/raw/sound_win")

    }



    private fun playSoundWithId(context: Context, id: String) {
        this.reset()
        setDataSource(context, Uri.parse(id));
        prepare()
        start()
    }

}