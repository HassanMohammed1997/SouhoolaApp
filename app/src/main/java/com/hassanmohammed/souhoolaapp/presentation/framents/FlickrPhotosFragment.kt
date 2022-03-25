package com.hassanmohammed.souhoolaapp.presentation.framents

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hassanmohammed.souhoolaapp.R
import com.hassanmohammed.souhoolaapp.databinding.FragmentFlickrPhotosBinding
import com.hassanmohammed.souhoolaapp.presentation.FlickerViewModel
import com.hassanmohammed.souhoolaapp.presentation.adapter.FlickrPhotosLoadStateAdapter
import com.hassanmohammed.souhoolaapp.presentation.adapter.FlikerPhotoPagingAdapter
import com.hassanmohammed.souhoolaapp.utils.collectFlowSafely
import com.hassanmohammed.souhoolaapp.utils.fragmentViewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class FlickrPhotosFragment : Fragment(R.layout.fragment_flickr_photos) {
    private val binding by fragmentViewBinding(FragmentFlickrPhotosBinding::bind)
    private val viewModel by viewModels<FlickerViewModel>()

    @Inject
    lateinit var flikerFlikerPhotoPagingPagingAdapter: FlikerPhotoPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPhotoRecyclerView()
        getFlickrPhotos()

    }

    private fun setupPhotoRecyclerView() {
        with(binding) {
            flickrPhotosRecycler.adapter =
                flikerFlikerPhotoPagingPagingAdapter.withLoadStateHeaderAndFooter(
                    header = FlickrPhotosLoadStateAdapter { flikerFlikerPhotoPagingPagingAdapter.retry() },
                    footer = FlickrPhotosLoadStateAdapter { flikerFlikerPhotoPagingPagingAdapter.retry() }
                )
        }
        flikerFlikerPhotoPagingPagingAdapter.setOnFlickrPhotoClickListener { photoUrl ->
            findNavController().navigate(
                FlickrPhotosFragmentDirections.actionFlickrPhotosFragmentToPhotoFullScreenFragment(
                    photoUrl
                )
            )
        }
    }

    private fun getFlickrPhotos() {
        collectFlowSafely {
            viewModel.getPhotos().collect {
                flikerFlikerPhotoPagingPagingAdapter.submitData(it)
            }
        }

    }


}