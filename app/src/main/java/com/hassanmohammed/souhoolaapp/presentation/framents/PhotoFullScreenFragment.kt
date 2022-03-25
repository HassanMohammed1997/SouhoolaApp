package com.hassanmohammed.souhoolaapp.presentation.framents

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.hassanmohammed.souhoolaapp.R
import com.hassanmohammed.souhoolaapp.databinding.FragmentPhotoFullScreenBinding
import com.hassanmohammed.souhoolaapp.utils.fragmentViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFullScreenFragment : Fragment(R.layout.fragment_photo_full_screen) {
    private val binding by fragmentViewBinding(FragmentPhotoFullScreenBinding::bind)
    private val args by navArgs<PhotoFullScreenFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.photoUrl = args.photoUrl
    }
}