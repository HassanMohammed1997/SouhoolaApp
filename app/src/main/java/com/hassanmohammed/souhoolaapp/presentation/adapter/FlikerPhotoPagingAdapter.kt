package com.hassanmohammed.souhoolaapp.presentation.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hassanmohammed.souhoolaapp.domain.models.Photo
import com.hassanmohammed.souhoolaapp.presentation.adapter.viewholders.AdBannerViewHolder
import com.hassanmohammed.souhoolaapp.presentation.adapter.viewholders.FlickrPhotoViewHolder
import javax.inject.Inject

private const val BANNER_VIEW_TYPE = 0
private const val PHOTO_VIEW_TYPE = 1

class FlikerPhotoPagingAdapter @Inject constructor() :
    PagingDataAdapter<Photo, RecyclerView.ViewHolder>(FLIKER_PHOTO_DIFF_UTIL) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            if (holder is FlickrPhotoViewHolder)
                holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == BANNER_VIEW_TYPE)
            AdBannerViewHolder.get(parent)
        else
            FlickrPhotoViewHolder.get(parent)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 5 == 0 && position > 0)
            BANNER_VIEW_TYPE
        else
            PHOTO_VIEW_TYPE
    }

    companion object {
        private val FLIKER_PHOTO_DIFF_UTIL = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }

        }
    }
}