package com.example.albums.data.model

import androidx.annotation.StringDef

@StringDef(FileType.TYPE_IMAGE, FileType.TYPE_VIDEO)
annotation class FileType {
    companion object {

        const val TYPE_IMAGE = "TYPE_IMAGE"

        const val TYPE_VIDEO = "TYPE_VIDEO"
    }
}


