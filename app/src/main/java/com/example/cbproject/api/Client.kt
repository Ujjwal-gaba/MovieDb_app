package com.example.cbproject.api

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class Client {

    val BASE_URL = "http://api.themoviedb.org/3/"





    fun getClient(): Retrofit? {
       return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



}
