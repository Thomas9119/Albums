package com.example.albums.view.picture

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albums.data.model.FileModel
import com.example.albums.data.model.ImageModel
import com.example.albums.data.model.VideoModel
import com.example.albums.data.repository.FileRepository
import com.example.albums.data.repository.FileRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PicturesViewModel : ViewModel() {

    private val fileRepository: FileRepository by lazy { FileRepositoryImpl() }
    private val _files = MutableLiveData<List<FileModel>>()
    val files: LiveData<List<FileModel>> = _files

    fun getListData(context: Context) {
        viewModelScope.launch(Dispatchers.Main) {
            val a = async(Dispatchers.IO) { fileRepository.getAllPhoto(context) }
            val b = async(Dispatchers.IO) { fileRepository.getAllVideo(context) }
            showData(a.await(), b.await())
        }
    }

    private fun showData(images: List<ImageModel>, videos: List<VideoModel>) {
        val data = ArrayList<FileModel>()

        data.addAll(images.map {
            FileModel().apply {
                this.name = it.albumName
                this.albumName = it.albumName
                this.path = it.imagePath
                this.type = it.type
                this.dateTimeCreated = it.dateModified
            }
        })

        data.addAll(videos.map {
            FileModel().apply {
                this.name = it.videoTitle
                this.albumName = it.albumName
                this.path = it.path
                this.type = it.type
                this.durationLong = it.numberDuration
                this.dateTimeCreated = it.dateModified
            }
        })
        Log.e("ttt", "showData: ${data.size}")
        data.sortBy { it.dateTimeCreated }
        _files.value = data
    }
}