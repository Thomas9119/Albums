package com.example.albums.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class FileModel : Parcelable {
    var name: String? = null
    var albumName: String? = null
    var path: String? = null
    var iconResource: String? = null
    var dateTimeCreated: Long = 0
    var type: String? = null
    var durationLong: Long = 0
}