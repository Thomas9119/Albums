package com.example.albums.data.repository

import android.content.Context
import com.example.albums.data.model.AlbumPhotoModel
import com.example.albums.data.model.FileModel
import com.example.albums.data.model.ImageModel
import com.example.albums.data.model.VideoModel
import com.example.albums.utils.StoreUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.*

class FileRepositoryImpl : FileRepository {

    override suspend fun getAllPhoto(context: Context): List<ImageModel> {
        return StoreUtil.getListPhoto(context)
    }

    override suspend fun getAllVideo(context: Context): List<VideoModel> {
        return StoreUtil.getListVideos(context)
    }

    override suspend fun getAlbums(context: Context): List<AlbumPhotoModel> {
//        val data = GlobalScope.async {
//            val photos = StoreUtil.getListPhoto(context)
//            val videos = StoreUtil.getListVideos(context)
//            mergeAll(photos, videos)
//        }

        val photos = StoreUtil.getListPhoto(context)
        val videos = StoreUtil.getListVideos(context)
//        mergeAll(photos, videos)
        return mergeAll(photos, videos)
//        return data.await()
    }

    private fun mergeAll(
        photos: List<ImageModel>,
        videos: List<VideoModel>
    ): List<AlbumPhotoModel> {
        val files: MutableList<FileModel> = ArrayList()
        val albumList: MutableList<AlbumPhotoModel> = ArrayList()
        val albumsNames: Vector<String> = Vector()

        files.addAll(photos.map {
            FileModel().apply {
                this.name = it.albumName
                this.albumName = it.albumName
                this.path = it.imagePath
                this.type = it.type
                this.dateTimeCreated = it.dateModified
            }
        })

        files.addAll(videos.map {
            FileModel().apply {
                this.name = it.videoTitle
                this.albumName = it.albumName
                this.path = it.path
                this.type = it.type
                this.durationLong = it.numberDuration
                this.dateTimeCreated = it.dateModified
            }
        })

        for (file in files) {
            if (albumsNames.contains(file.albumName)) {
                for (album in albumList) {
                    if (album.name.equals(file.albumName)) {
                        album.photos.add(file)
                        break
                    }
                }

            } else {
                val albumPhotoModel = AlbumPhotoModel().apply {
                    this.id = id
                    this.name = file.albumName
                    this.photos.add(file)
                }
                albumList.add(albumPhotoModel)
                albumsNames.add(file.albumName)
            }
        }
        return albumList
    }
}

