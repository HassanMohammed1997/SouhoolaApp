package com.hassanmohammed.souhoolaapp.data.remote

import com.hassanmohammed.souhoolaapp.BuildConfig
import com.hassanmohammed.souhoolaapp.domain.models.FlickrPhoto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {
    @GET("rest/")
    suspend fun getPhotos(
        @Query("text") text: String,
        @Query("method") method: String = "flickr.photos.search",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 50,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20,
        @Query("tags") tags: String = "movies, movie",
        @Query("api_key") apiKey: String = BuildConfig.FLICKR_API_KEY
    ) : Response<FlickrPhoto>
}