package com.hassanmohammed.souhoolaapp.presentation.adapter.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hassanmohammed.souhoolaapp.databinding.ListItemFlikerPhotoBinding
import com.hassanmohammed.souhoolaapp.domain.models.Photo

class FlickrPhotoViewHolder(private val binding: ListItemFlikerPhotoBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun get(parent: ViewGroup): FlickrPhotoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemFlikerPhotoBinding.inflate(inflater, parent, false)
            return FlickrPhotoViewHolder(binding)
        }
    }

    fun bind(photo: Photo) {
        with(binding) {
            this.photo = photo
            executePendingBindings()
        }
    }
}