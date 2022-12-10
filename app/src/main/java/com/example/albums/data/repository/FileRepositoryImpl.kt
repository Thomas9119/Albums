package com.example.albums.data.repository

import android.content.Context
import com.example.albums.data.model.AlbumPhotoModel
import com.example.albums.data.model.ImageModel
import com.example.albums.data.model.VideoModel
import com.example.albums.utils.StoreUtil

class FileRepositoryImpl : FileRepository {

    override suspend fun getAllPhoto(context: Context): List<ImageModel> {
        return StoreUtil.getListPhoto(context)
    }

    override suspend fun getAllVideo(context: Context): List<VideoModel> {
        return StoreUtil.getListVideos(context)
    }

    override suspend fun getAlbums(context: Context): List<AlbumPhotoModel> {
        return StoreUtil.getAlbumPhoto(context)
    }
}

