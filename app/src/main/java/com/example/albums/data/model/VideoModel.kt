package com.example.albums.data.model

import android.net.Uri

class VideoModel {
    var videoTitle: String? = null
    var videoDuration: String? = null
    var videoUri: Uri? = null
    var path: String? = null
    var type: String = FileType.TYPE_VIDEO
    var numberDuration: Long = 0
}