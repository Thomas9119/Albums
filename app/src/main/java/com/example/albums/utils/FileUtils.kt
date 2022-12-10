package com.example.albums.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.core.content.FileProvider
import com.example.albums.BuildConfig
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
}