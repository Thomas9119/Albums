package com.example.albums.data.model

data class ImageModel(
    var imagePath: String? = null,
    var albumName: String? = null,
    var type: String = FileType.TYPE_IMAGE,
    var dateModified: Long = 0
)
