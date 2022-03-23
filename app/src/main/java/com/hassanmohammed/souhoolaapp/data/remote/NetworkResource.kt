package com.hassanmohammed.souhoolaapp.data.remote

import androidx.annotation.StringRes
import com.hassanmohammed.souhoolaapp.R

sealed class NetworkResource<T>(
    val data: T? = null,
    val message: String? = null,
    val code: Int? = null,
    @StringRes val messageRes: Int? = null
) {
    class Loading<T>(data: T? = null) : NetworkResource<T>(data)
    class Success<T>(data: T) : NetworkResource<T>(data = data)
    class ServerError<T>(
        message: String,
        @StringRes messageRes: Int = R.string.something_went_error,
        data: T? = null,
        code: Int? = null,
    ) :
        NetworkResource<T>(data = data, message = message, messageRes = messageRes, code = code)

    class NetworkError<T>(
        @StringRes messageRes: Int = R.string.no_internet_connection,
        message: String? = "No internet connection",
    ) : NetworkResource<T>(messageRes = messageRes, message = message)


}