package no.jahnsrud.tictactoe

import android.R
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.content.res.AssetFileDescriptor



object SoundEffectPlayer : MediaPlayer() {

    fun playNextSound(context: Context) {
        setDataSource(context, Uri.parse("android.resource://no.jahnsrud.tictactoe/raw/sound_next"));
        // prepareAsync()
        start()
    }

}