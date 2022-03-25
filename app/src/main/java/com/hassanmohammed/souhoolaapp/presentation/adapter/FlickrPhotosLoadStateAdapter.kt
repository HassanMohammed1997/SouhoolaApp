package com.hassanmohammed.souhoolaapp.presentation.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.hassanmohammed.souhoolaapp.presentation.adapter.viewholders.LoadStateFooterViewHolder

class FlickrPhotosLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadStateFooterViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateFooterViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateFooterViewHolder = LoadStateFooterViewHolder.get(parent, retry)
}