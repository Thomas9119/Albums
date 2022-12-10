package com.example.albums.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.view.View
import android.widget.ImageView
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.albums.BuildConfig
import com.example.albums.R
import com.example.albums.data.model.FileModel
import com.example.albums.data.model.FileType
import java.io.File


object FileUtils {

    fun openFile(context: Context, file: File) {
        val intent = Intent(Intent.ACTION_VIEW) //
            .setDataAndType(
                if (VERSION.SDK_INT >= VERSION_CODES.N) FileProvider.getUriForFile(
                    context,
                    BuildConfig.APPLICATION_ID + ".provider",
                    file
                ) else Uri.fromFile(file),
                "image/*"
            ).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        context.startActivity(intent)
    }

    fun openVideo(context: Context, file: File) {
        val intent = Intent(Intent.ACTION_VIEW) //
            .setDataAndType(
                if (VERSION.SDK_INT >= VERSION_CODES.N) FileProvider.getUriForFile(
                    context,
                    BuildConfig.APPLICATION_ID + ".provider",
                    file
                ) else Uri.fromFile(file), "video/*"
            ).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        context.startActivity(intent)
    }

    fun loadImage(context: Context, file: FileModel, view: ImageView, width: Int = 200) {
        if (file.type == FileType.TYPE_IMAGE) {
            Glide.with(context).load(file.path)
                .apply(
                    RequestOptions()
                        .override(width, width)
                ).into(view)
        } else {
            val uri = Uri.fromFile(File(file.path))
            Glide.with(context).load(uri)
                .thumbnail(0.1f)
                .error(R.drawable.ic_no_image)
                .apply(
                    RequestOptions()
                        .override(width, width)
                ).into(view)
        }

    }
}