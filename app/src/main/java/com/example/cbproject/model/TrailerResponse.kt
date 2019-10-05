package com.example.cbproject.model

import com.google.gson.annotations.SerializedName

data class TrailerResponse (

//    @SerializedName("id")
    var idTrailer: Int? ,
        //private set
//    @SerializedName("results")
   var results: List<Trailer>?
){

   internal fun setIdTrailer(id_trailer: Int) {
        this.idTrailer = id_trailer
    }

   internal fun getIdTrailer(): Int? {
        return idTrailer
    }



    internal fun getResults(): List<Trailer>? {
        return results
    }
}