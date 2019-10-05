package com.example.cbproject.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Review : Parcelable {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("results")
    @Expose
    var results: List<ReviewResult>? = null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null

    protected constructor(`in`: Parcel) {
        this.id = `in`.readValue(Int::class.java.classLoader) as Int
        this.page = `in`.readValue(Int::class.java.classLoader) as Int
        `in`.readList(this.results!!, com.example.cbproject.model.ReviewResult::class.java!!.getClassLoader())
        this.totalPages = `in`.readValue(Int::class.java.classLoader) as Int
        this.totalResults = `in`.readValue(Int::class.java.classLoader) as Int
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param id
     * @param results
     * @param totalResults
     * @param page
     * @param totalPages
     */
    constructor(id: Int?, page: Int?, results: List<ReviewResult>, totalPages: Int?, totalResults: Int?) : super() {
        this.id = id
        this.page = page
        this.results = results
        this.totalPages = totalPages
        this.totalResults = totalResults
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(id)
        dest.writeValue(page)
        dest.writeList(results)
        dest.writeValue(totalPages)
        dest.writeValue(totalResults)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @SuppressLint("ParcelCreator")
        val CREATOR = object : Parcelable.Creator<Review> {


            override fun createFromParcel(`in`: Parcel): Review {
                return Review(`in`)
            }

            override fun newArray(size: Int): Array<Review?> {
                return arrayOfNulls(size)
            }

        }
    }



}