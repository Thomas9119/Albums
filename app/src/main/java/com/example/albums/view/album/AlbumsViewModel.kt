package com.example.albums.view.album

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albums.data.model.AlbumPhotoModel
import com.example.albums.data.repository.FileRepository
import com.example.albums.data.repository.FileRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumsViewModel : ViewModel() {

    private val fileRepository: FileRepository by lazy { FileRepositoryImpl() }
    private val _files = MutableLiveData<List<AlbumPhotoModel>>()
    val files: LiveData<List<AlbumPhotoModel>> = _files

    fun getAlbums(context: Context) {
        viewModelScope.launch(Dispatchers.Main) {
            val albums = fileRepository.getAlbums(context)
            _files.value = albums
        }
    }
}