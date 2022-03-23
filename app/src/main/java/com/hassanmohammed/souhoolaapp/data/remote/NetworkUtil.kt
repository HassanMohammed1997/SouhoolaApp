package com.hassanmohammed.souhoolaapp.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

fun <T> safeApiCall(
    call: suspend () -> T
): Flow<NetworkResource<T>> = flow {
    emit(NetworkResource.Loading())
    try {
        val response = call()
        emit(NetworkResource.Success(response))
    } catch (e: HttpException) {
        emit(
            NetworkResource.ServerError(
                message = e.localizedMessage ?: "Something went error",
                code = e.code()
            )
        )
    } catch (e: IOException) {
        emit(NetworkResource.NetworkError())
    }
}