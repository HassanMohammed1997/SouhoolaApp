package com.hassanmohammed.souhoolaapp.presentation.adapter.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.hassanmohammed.souhoolaapp.databinding.LoadStateFooterViewItemBinding

class LoadStateFooterViewHolder(
    private val binding: LoadStateFooterViewItemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun get(parent: ViewGroup, retry: () -> Unit): LoadStateFooterViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = LoadStateFooterViewItemBinding.inflate(inflater, parent, false)
            return LoadStateFooterViewHolder(binding, retry)
        }
    }

    init {
        binding.retryButton.setOnClickListener {
            retry.invoke()
        }
    }

    fun bind(loadState: LoadState) {
        with(binding) {
            if (loadState is LoadState.Error)
                errorMsg.text = loadState.error.localizedMessage
            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            errorMsg.isVisible = loadState is LoadState.Error
            executePendingBindings()
        }
    }
}