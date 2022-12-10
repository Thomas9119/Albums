package com.example.albums.data.repository

import android.content.Context
import com.example.albums.data.model.AlbumPhotoModel
import com.example.albums.data.model.ImageModel
import com.example.albums.data.model.VideoModel

interface FileRepository {
    suspend fun getAllPhoto(context: Context): List<ImageModel>
    suspend fun getAllVideo(context: Context): List<VideoModel>
    suspend fun getAlbums(context: Context): List<AlbumPhotoModel>
}

