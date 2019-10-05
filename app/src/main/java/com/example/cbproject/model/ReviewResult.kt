package com.example.cbproject.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReviewResult : Parcelable {

    @SerializedName("author")
    @Expose
    var author: String? = null
    @SerializedName("content")
    @Expose
    var content: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null

    protected constructor(`in`: Parcel) {
        this.author = `in`.readValue(String::class.java.classLoader) as String
        this.content = `in`.readValue(String::class.java.classLoader) as String
        this.id = `in`.readValue(String::class.java.classLoader) as String
        this.url = `in`.readValue(String::class.java.classLoader) as String
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param id
     * @param content
     * @param author
     * @param url
     */
    constructor(author: String, content: String, id: String, url: String) : super() {
        this.author = author
        this.content = content
        this.id = id
        this.url = url
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(author)
        dest.writeValue(content)
        dest.writeValue(id)
        dest.writeValue(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {

        @SuppressLint("ParcelCreator")
        val CREATOR = object : Parcelable.Creator<ReviewResult> {


            override fun createFromParcel(`in`: Parcel): ReviewResult {
                return ReviewResult(`in`)
            }

            override fun newArray(size: Int): Array<ReviewResult?> {
                return arrayOfNulls(size)
            }

        }
    }

}