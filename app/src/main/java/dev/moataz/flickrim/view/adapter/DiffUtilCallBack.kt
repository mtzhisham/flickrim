package dev.moataz.flickrim.view.adapter

import androidx.recyclerview.widget.DiffUtil
import dev.moataz.flickrim.model.data.Photo

class DiffUtilCallBack : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id.equals(newItem.id)
    }
}