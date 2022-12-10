package com.example.albums.view.picture

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.albums.data.model.FileModel
import com.example.albums.data.model.FileType
import com.example.albums.databinding.ItemFileBinding
import com.example.albums.utils.ScreenUtil
import com.example.albums.utils.StoreUtil
import java.io.File

class PhotoItemAdapter(val func: (FileModel) -> Unit) :
    RecyclerView.Adapter<PhotoItemAdapter.ViewHolder>() {
    private val values: ArrayList<FileModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun addData(values: List<FileModel>) {
        this.values.clear()
        this.values.addAll(values)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: ItemFileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                func.invoke(values[absoluteAdapterPosition])
            }
        }

        fun bind(photo: FileModel) {
            val width = ScreenUtil.getScreenWidth(binding.root.context) / 3
            if (photo.type == FileType.TYPE_IMAGE) {
                binding.tvContent.visibility = View.GONE
                Glide.with(binding.root.context).load(photo.path)
                    .apply(
                        RequestOptions()
                            .override(width, width)
                    ).into(binding.ivPhoto)
            } else {
                binding.tvContent.visibility = View.VISIBLE
                binding.tvContent.text = StoreUtil.timeConversion(photo.durationLong)
                val uri = Uri.fromFile(File(photo.path))
                Glide.with(binding.root.context).load(uri)
                    .thumbnail(0.1f)
                    .apply(
                        RequestOptions()
                            .override(width, width)
                    ).into(binding.ivPhoto)
            }
        }
    }

}