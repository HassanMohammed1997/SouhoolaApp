package com.hassanmohammed.souhoolaapp.presentation.ui.framents

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hassanmohammed.souhoolaapp.R
import com.hassanmohammed.souhoolaapp.databinding.FragmentFlickrPhotosBinding
import com.hassanmohammed.souhoolaapp.presentation.FlickerViewModel
import com.hassanmohammed.souhoolaapp.presentation.adapter.FlickrPhotosLoadStateAdapter
import com.hassanmohammed.souhoolaapp.presentation.adapter.FlikerPhotoPagingAdapter
import com.hassanmohammed.souhoolaapp.utils.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class FlickrPhotosFragment : Fragment(R.layout.fragment_flickr_photos) {
    private val binding by fragmentViewBinding(FragmentFlickrPhotosBinding::bind)
    private val viewModel by viewModels<FlickerViewModel>()

    @Inject
    lateinit var flikerFlikerPhotoPagingPagingAdapter: FlikerPhotoPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPhotoRecyclerView()
        fetchFlickrPhotos()
        if (!isNetworkAvailable)
            showSnackbar(R.string.you_are_offline)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        val searchMenuItem = menu.findItem(R.id.search)
        val searchView = searchMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    fetchFlickrPhotos(it)
                }
                hideKeyboard()

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

    }

    private fun fetchFlickrPhotos(query: String = "") {
        collectFlowSafely {
            viewModel.getPhotos(query).collect {
                flikerFlikerPhotoPagingPagingAdapter.submitData(it)
            }
        }

    }

}