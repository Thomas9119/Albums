package com.example.albums.data.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class FileModel : Parcelable {
     var name: String? = null
     var path: String? = null
     var iconResource: String? = null
     val dateTimeCreated: String? = null
     val duration: String? = null
     val isSelected = false
     val indexNumber = 0
     val uri: Uri? = null
     var type: String? = null
     var durationLong: Long = 0
}