package com.hassanmohammed.souhoolaapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hassanmohammed.souhoolaapp.R

class AdBannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun get(viewGroup: ViewGroup): AdBannerViewHolder {
            val inflater = LayoutInflater.from(viewGroup.context)
            val itemView = inflater.inflate(R.layout.list_item_banner, viewGroup, false)
            return AdBannerViewHolder(itemView)
        }
    }
}