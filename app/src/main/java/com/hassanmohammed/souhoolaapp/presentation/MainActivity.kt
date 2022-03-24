package com.hassanmohammed.souhoolaapp.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.hassanmohammed.souhoolaapp.R
import com.hassanmohammed.souhoolaapp.presentation.adapter.FlikerPhotoPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<FlickerViewModel>()

    @Inject
    lateinit var flikerFlikerPhotoPagingPagingAdapter: FlikerPhotoPagingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<RecyclerView>(R.id.flickr_photos_recycler).apply {
            adapter = flikerFlikerPhotoPagingPagingAdapter
        }
        lifecycleScope.launch {
            viewModel.getPhotos().collect {
                flikerFlikerPhotoPagingPagingAdapter.submitData(it)
            }
        }

    }
}