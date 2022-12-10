package com.example.albums.utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.example.albums.data.model.AlbumPhotoModel
import com.example.albums.data.model.FileType
import com.example.albums.data.model.ImageModel
import com.example.albums.data.model.VideoModel
import java.util.*

object StoreUtil {

    fun getAlbumPhoto(context: Context): List<AlbumPhotoModel> {
        val albumList: MutableList<AlbumPhotoModel> = ArrayList()
        val albumsNames: Vector<String> = Vector()
        val columns = arrayOf(
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATE_MODIFIED
        )
        val orderBy = MediaStore.Images.Media.DATE_MODIFIED + " DESC"
        //Stores all the images from the gallery in Cursor
        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
            null, orderBy
        )
        //Total number of images
        cursor?.let {
            if (cursor.moveToFirst()) {
                do {
                    val idIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID)
                    val bucketIndex = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
                    val dataIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                    val id = cursor.getString(idIndex)
                    val bucket = cursor.getString(bucketIndex)
                    val data = cursor.getString(dataIndex)

                    if (id != null && bucket != null && data != null) {
                        val imageModel = ImageModel(
                            albumName = bucket,
                            imagePath = data,
                            type = FileType.TYPE_IMAGE
                        )
                        if (albumsNames.contains(bucket)) {
                            for (album in albumList) {
                                if (album.name.equals(bucket)) {
                                    album.photos.add(imageModel)
                                    Log.i(
                                        "ttt",
                                        "A photo was added to album => $bucket"
                                    )
                                    break
                                }
                            }

                        } else {
                            val albumPhotoModel = AlbumPhotoModel().apply {
                                this.id = id
                                this.name = bucket
                                this.photos.add(imageModel)
                            }
                            albumList.add(albumPhotoModel)
                            albumsNames.add(bucket)
                        }
                    }
                } while (cursor.moveToNext())
            }
        }

        return albumList
    }

    fun getListPhoto(context: Context): List<ImageModel> {
        val photoList: MutableList<ImageModel> = ArrayList()
        val columns = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID)
        val orderBy = MediaStore.Images.Media._ID
        //Stores all the images from the gallery in Cursor
        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
            null, orderBy
        )
        //Total number of images
        val count = cursor!!.count

        //Create an array to store path to all the images
        val arrPath = arrayOfNulls<String>(count)
        for (i in 0 until count) {
            cursor.moveToPosition(i)
            val dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
            //Store the path of the image
            arrPath[i] = cursor.getString(dataColumnIndex)
            val imageModel = ImageModel(
                imagePath = arrPath[i],
                type = FileType.TYPE_IMAGE
            )
            photoList.add(imageModel)
        }
        // The cursor should be freed up after use with close()
        cursor.close()
        return photoList
    }


    fun getListVideos(context: Context): List<VideoModel> {
        val videoArrayList: MutableList<VideoModel> = ArrayList<VideoModel>()
        val contentResolver = context.contentResolver
        val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val cursor = contentResolver.query(uri, null, null, null, null)

        //looping through all rows and adding to list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                val titleIndex = cursor.getColumnIndex(MediaStore.Video.Media.TITLE)
                val durationIndex = cursor.getColumnIndex(MediaStore.Video.Media.DURATION)
                val dataIndex = cursor.getColumnIndex(MediaStore.Video.Media.DATA)
                val title = cursor.getString(titleIndex)
                val duration = cursor.getString(durationIndex)
                val data = cursor.getString(dataIndex)
                if (title != null && duration != null && data != null) {
                    val videoModel = VideoModel().apply {
                        videoTitle = title
                        videoUri = Uri.parse(data)
                        numberDuration = duration.toLong()
                        videoDuration = timeConversion(duration.toLong())
                        path = data
                        type = FileType.TYPE_VIDEO
                    }
                    videoArrayList.add(videoModel)
                }
            } while (cursor.moveToNext())
        }
        return videoArrayList
    }

    //time conversion
    fun timeConversion(value: Long): String {
        val videoTime: String
        val dur = value.toInt()
        val hrs = dur / 3600000
        val mns = dur / 60000 % 60000
        val scs = dur % 60000 / 1000
        videoTime = if (hrs > 0) {
            String.format("%02d:%02d:%02d", hrs, mns, scs)
        } else {
            String.format("%02d:%02d", mns, scs)
        }
        return videoTime
    }
}