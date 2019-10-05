package com.example.cbproject.api

import retrofit2.http.GET
import com.example.cbproject.model.MoviesResponse
import com.example.cbproject.model.Review
import com.example.cbproject.model.TrailerResponse
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query





interface Service {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Call<MoviesResponse>


    @GET("movie/{movie_id}/videos")
    fun getMovieTrailer(@Path("movie_id") id: Int, @Query("api_key") apiKey: String): Call<TrailerResponse>

//    //Reviews
    @GET("movie/{movie_id}/reviews")
    fun getReview(@Path("movie_id") id: Int, @Query("api_key") apiKey: String): Call<Review>

}