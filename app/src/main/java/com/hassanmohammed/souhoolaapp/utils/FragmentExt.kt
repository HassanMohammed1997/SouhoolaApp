package com.hassanmohammed.souhoolaapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

fun Fragment.hideKeyboard(){
    requireActivity().currentFocus?.let {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(it.windowToken, 0)
    }
}
val Fragment.isNetworkAvailable: Boolean
    get() {
        val connectivityManager =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

fun Fragment.collectFlowSafely(func: suspend () -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            func()
        }
    }
}

fun Fragment.showSnackbar(message: String?) {
    message?.let {
        Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG)
            .show()
    }
}

fun Fragment.showSnackbar(@StringRes res: Int?) {
    showSnackbar(res?.let { getString(it) })
}