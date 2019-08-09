package com.chenfd.alphamovie

import android.content.Context
import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.util.AttributeSet
import android.util.Log
import android.view.Surface
import android.view.TextureView
import java.io.File

/**
 *
 * Created by chen on 2019/8/9
 * Email: chenfd99@qq.com
 */
class AlphaMovieView : TextureView, TextureView.SurfaceTextureListener {
    private val TAG = this::class.java.simpleName
    private var zipFile: File? = null
    private var mMediaPlayer: MediaPlayer

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        surfaceTextureListener = this
        alpha = 0.5f
        mMediaPlayer = MediaPlayer()
    }

    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
        try {
            if (mMediaPlayer.isPlaying)
                mMediaPlayer.stop()
            mMediaPlayer.release()
        } catch (e: Exception) {
        }
        return true
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
        try {
            if (surface != null)
                mMediaPlayer.setSurface(Surface(surface))
        } catch (e: Exception) {
        }
    }


    fun onResume() {
        try {
            if (zipFile?.exists() == true)
                mMediaPlayer.start()
        } catch (e: Exception) {
        }
    }

    fun onPause() {
        try {
            mMediaPlayer.pause()
        } catch (e: Exception) {
        }
    }


    fun stopPlayEffect() {
        try {
            mMediaPlayer.stop()
        } catch (e: Exception) {
        }
    }


    fun startPlay(videoUrl: String) {
        try {
            mMediaPlayer.setDataSource(videoUrl)
            mMediaPlayer.setOnPreparedListener {
                mMediaPlayer.start()
            }
            mMediaPlayer.setOnCompletionListener {
                it.start()
            }
            mMediaPlayer.setOnErrorListener { mp, what, extra ->
                false
            }

            mMediaPlayer.prepare()
            mMediaPlayer.start()
        } catch (e: Exception) {
            Log.e(TAG, e.message ?: "")
        }
    }


}
