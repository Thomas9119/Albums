package com.example.albums.view.album.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.albums.R
import com.example.albums.data.model.AlbumPhotoModel
import com.example.albums.databinding.ItemAlbumBinding

class AlbumItemAdapter constructor(val func: (AlbumPhotoModel) -> Unit) :
    RecyclerView.Adapter<AlbumItemAdapter.ViewHolder>() {
    private val values: ArrayList<AlbumPhotoModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun addData(values: List<AlbumPhotoModel>) {
        this.values.clear()
        this.values.addAll(values)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                func.invoke(values[absoluteAdapterPosition])
            }
        }

        fun bind(album: AlbumPhotoModel) {
            val size = album.photos.size
            binding.tvName.text = album.name
            binding.tvQuantity.text = size.toString()
            if (size > 0) {
                Glide.with(binding.root.context).load(album.photos[0].imagePath).into(binding.ivAlbum)
            } else {
                Glide.with(binding.root.context).load(R.drawable.ic_no_image).into(binding.ivAlbum)
            }
        }
    }

}