package com.hassanmohammed.souhoolaapp.presentation.adapter.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hassanmohammed.souhoolaapp.R
import com.hassanmohammed.souhoolaapp.databinding.ListItemFlikerPhotoBinding
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import com.hassanmohammed.souhoolaapp.presentation.adapter.OnFlickrPhotoClickListener

class FlickrPhotoViewHolder(
    private val binding: ListItemFlikerPhotoBinding,
    private val listener: OnFlickrPhotoClickListener?
) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun get(parent: ViewGroup, listener: OnFlickrPhotoClickListener?): FlickrPhotoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemFlikerPhotoBinding.inflate(inflater, parent, false)
            return FlickrPhotoViewHolder(binding, listener)
        }
    }

    fun bind(photo: Photo) {
        with(binding) {
            this.photo = photo
            executePendingBindings()
        }
        itemView.setOnClickListener {
            listener?.onPhotoClicked(
                it.context.getString(
                    R.string.fliker_photo_url,
                    photo.farm.toString(),
                    photo.server,
                    photo.id,
                    photo.secret
                )
            )
        }
    }
}