package com.yuvademos.coroutinesapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yuvademos.coroutinesapp.model.PlayList
import com.yuvademos.coroutinesapp.databinding.PlaylistItemBinding

class MyPlayListRecyclerViewAdapter(
    private val values: List<PlayList>
) : RecyclerView.Adapter<MyPlayListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.playListName.text = item.name
        holder.category.text = item.category
        holder.image.setImageResource(item.image)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: PlaylistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val playListName: TextView = binding.playlistName
        val category: TextView = binding.playlistCategory
        val image: ImageView = binding.playlistImage

        override fun toString(): String {
            return super.toString() + " '" + playListName.text + "'"
        }
    }

}