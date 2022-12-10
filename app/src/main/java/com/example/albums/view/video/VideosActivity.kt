package com.example.albums.view.video

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.albums.R
import com.example.albums.view.video.ui.videos.VideosFragment

class VideosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videos)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, VideosFragment.newInstance())
                .commitNow()
        }
    }
}