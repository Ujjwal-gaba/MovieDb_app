package com.example.cbproject.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import android.R.id
import java.io.Serializable


    class Movie : Parcelable {
        @SerializedName("poster_path")
        var posterPath: String? = null
        @SerializedName("adult")
        var isAdult: Boolean = false
        @SerializedName("overview")
        var overview: String? = null
        @SerializedName("release_date")
        var releaseDate: String? = null
        @SerializedName("genre_ids")
        var genreIds: List<Int> = ArrayList()
        @SerializedName("id")
        var id: Int? = null
        @SerializedName("original_title")
        var originalTitle: String? = null
        @SerializedName("original_language")
        var originalLanguage: String? = null
        @SerializedName("title")
        var title: String? = null
        @SerializedName("backdrop_path")
        var backdropPath: String? = null
        @SerializedName("popularity")
        var popularity: Double? = null
        @SerializedName("vote_count")
        var voteCount: Int? = null
        @SerializedName("video")
        var video: Boolean? = null
        @SerializedName("vote_average")
        var voteAverage: Double? = null

        constructor(
            posterPath: String, adult: Boolean, overview: String, releaseDate: String, genreIds: List<Int>, id: Int?,
            originalTitle: String, originalLanguage: String, title: String, backdropPath: String, popularity: Double?,
            voteCount: Int?, video: Boolean?, voteAverage: Double?
        ) {
            this.posterPath = posterPath
            this.isAdult = adult
            this.overview = overview
            this.releaseDate = releaseDate
            this.genreIds = genreIds
            this.id = id
            this.originalTitle = originalTitle
            this.originalLanguage = originalLanguage
            this.title = title
            this.backdropPath = backdropPath
            this.popularity = popularity
            this.voteCount = voteCount
            this.video = video
            this.voteAverage = voteAverage
        }

        constructor() {

        }

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(this.posterPath)
            dest.writeByte(if (this.isAdult) 1.toByte() else 0.toByte())
            dest.writeString(this.overview)
            dest.writeString(this.releaseDate)
            dest.writeList(this.genreIds)
            dest.writeValue(this.id)
            dest.writeString(this.originalTitle)
            dest.writeString(this.originalLanguage)
            dest.writeString(this.title)
            dest.writeString(this.backdropPath)
            dest.writeValue(this.popularity)
            dest.writeValue(this.voteCount)
            dest.writeValue(this.video)
            dest.writeValue(this.voteAverage)
        }

        internal fun getPosterPath(): String? {
            return posterPath
        }

        internal fun setPosterPath(posterPath: String) {
            this.posterPath = posterPath
        }

        internal fun isAdult(): Boolean? {
            return isAdult
        }

        internal fun setAdult(adult: Boolean) {
            this.isAdult = adult
        }

        internal fun getOverview(): String? {
            return overview
        }

        internal fun setOverview(overview: String) {
            this.overview = overview
        }

        internal fun getReleaseDate(): String? {
            return releaseDate
        }

        internal fun setReleaseDate(releaseDate: String) {
            this.releaseDate = releaseDate
        }

        internal fun getGenreIds(): List<Int?> {
            return genreIds
        }

        internal fun setGenreIds(genreIds: List<Int>) {
            this.genreIds = genreIds
        }

        internal fun getId(): Int? {
            return id
        }

        internal fun setId(id: Int?) {
            this.id = id
        }

        internal fun getOriginalTitle(): String? {
            return originalTitle
        }

        internal fun setOriginalTitle(originalTitle: String) {
            this.originalTitle = originalTitle
        }

        internal fun getOriginalLanguage(): String? {
            return originalLanguage
        }

        internal fun setOriginalLanguage(originalLanguage: String) {
            this.originalLanguage = originalLanguage
        }

        internal fun getTitle(): String? {
            return title
        }

        internal fun setTitle(title: String) {
            this.title = title
        }

        internal fun getBackdropPath(): String? {
            return backdropPath
        }

        internal fun setBackdropPath(backdropPath: String) {
            this.backdropPath = backdropPath
        }

        internal fun getPopularity(): Double? {
            return popularity
        }

        internal fun setPopularity(popularity: Double?) {
            this.popularity = popularity
        }

        internal fun getVoteCount(): Int? {
            return voteCount
        }

        internal fun setVoteCount(voteCount: Int?) {
            this.voteCount = voteCount
        }

        internal fun getVideo(): Boolean? {
            return video
        }

        internal fun setVideo(video: Boolean?) {
            this.video = video
        }

        internal fun getVoteAverage(): Double? {
            return voteAverage
        }

        internal fun setVoteAverage(voteAverage: Double?) {
            this.voteAverage = voteAverage
        }

        protected constructor(`in`: Parcel) {
            this.posterPath = `in`.readString()
            this.isAdult = `in`.readByte().toInt() != 0
            this.overview = `in`.readString()
            this.releaseDate = `in`.readString()
            this.genreIds = ArrayList()
            `in`.readList(this.genreIds, Int::class.java.classLoader)
            this.id = `in`.readValue(Int::class.java.classLoader) as Int
            this.originalTitle = `in`.readString()
            this.originalLanguage = `in`.readString()
            this.title = `in`.readString()
            this.backdropPath = `in`.readString()
            this.popularity = `in`.readValue(Double::class.java.classLoader) as Double
            this.voteCount = `in`.readValue(Int::class.java.classLoader) as Int
            this.video = `in`.readValue(Boolean::class.java.classLoader) as Boolean
            this.voteAverage = `in`.readValue(Double::class.java.classLoader) as Double
        }

//        companion object {
//
//            val BY_NAME_ALPHABETICAL: Comparator<Movie> = object : Comparator<Movie> {
//                override fun compare(movie: Movie, t1: Movie): Int {
//
//                    return movie.originalTitle!!.compareTo(t1.originalTitle!!)
//                }
//            }



           companion  object CREATOR  : Parcelable.Creator<Movie> {
                override fun createFromParcel(source: Parcel): Movie {
                    return Movie(source)
                }

                override fun newArray(size: Int): Array<Movie?> {
                    return arrayOfNulls(size)
                }
            }
        }

//    object CREATOR : Parcelable.Creator<Movie> {
//    override fun createFromParcel(parcel: Parcel): Movie {
//        return Movie()
//    }
//
//    override fun newArray(size: Int): Array<Movie?> {
//        return arrayOfNulls(size)
//    }



//    companion object  CREATOR : Parcelable.Creator<Movie> {
//        override fun createFromParcel(parcel: Parcel): Movie {
//            return Movie(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Movie?> {
//            return arrayOfNulls(size)
//        }
//    }
//    companion object CREATOR : Parcelable.Creator<Movie> {
//        override fun createFromParcel(parcel: Parcel): Movie {
//            return Movie(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Movie?> {
//            return arrayOfNulls(size)
//        }
//    }
//        companion object CREATOR : Parcelable.Creator<Movie> {
//            override fun createFromParcel(parcel: Parcel): Movie {
//                return Movie(parcel)
//            }
//
//            override fun newArray(size: Int): Array<Movie?> {
//                return arrayOfNulls(size)
//            }
//        }
//    }


//data class Movie(var vote_count: Number?, var id: Int?, var video: Boolean?, var vote_average: Double?, var title: String?, var popularity: Number?, var poster_path: String?, var original_language: String?, var original_title: String?, var genre_ids: List<Number>?, var backdrop_path: String?, var adult: Boolean?, var overview: String?, var release_date: String?){
//
//
//    internal fun getPosterPath(): String? {
//        return poster_path
//    }
//
//    internal fun setPosterPath(poster_path: String) {
//        this.poster_path = poster_path
//    }
//
//    internal fun isAdult(): Boolean? {
//        return adult
//    }
//
//    internal fun setAdult(adult: Boolean) {
//        this.adult = adult
//    }
//
//    internal fun getOverview(): String? {
//        return overview
//    }
//
//    internal fun setOverview(overview: String) {
//        this.overview = overview
//    }
//
//    internal fun getReleaseDate(): String? {
//        return release_date
//    }
//
//    internal fun setReleaseDate(release_date: String) {
//        this.release_date = release_date
//    }
//
//    internal fun getGenreIds(): List<Number>? {
//        return genre_ids
//    }
//
//    internal fun setGenreIds(genre_ids: List<Number>?) {
//        this.genre_ids = genre_ids
//    }
//
//    internal fun getId(): Int? {
//        return id
//    }
//
//    internal fun setId(id: Int?) {
//        this.id = id
//    }
//
//    internal fun getOriginalTitle(): String? {
//        return original_title
//    }
//
//    internal fun setOriginalTitle(originalTitle: String) {
//        this.original_title = originalTitle
//    }
//
//    internal fun getOriginalLanguage(): String ?{
//        return original_language
//    }
//
//    internal fun setOriginalLanguage(original_language: String) {
//        this.original_language = original_language
//    }
//
//    internal fun getTitle(): String? {
//        return title
//    }
//
//    internal fun setTitle(title: String) {
//        this.title = title
//    }
//
//    internal fun getBackdropPath(): String ?{
//        return backdrop_path
//    }
//
//    internal  fun setBackdropPath(backdrop_path: String) {
//        this.backdrop_path = backdrop_path
//    }
//
//    internal fun getPopularity(): Number? {
//        return popularity
//    }
//
//    internal  fun setPopularity(popularity: Number?) {
//        this.popularity = popularity
//    }
//
//    internal  fun getVoteCount(): Number? {
//        return vote_count
//    }
//
//    internal  fun setVoteCount(vote_count: Int?) {
//        this.vote_count = vote_count
//    }
//
//    internal  fun getVideo(): Boolean? {
//        return video
//    }
//
//    internal  fun setVideo(video: Boolean?) {
//        this.video = video
//    }
//
//    internal fun getVoteAverage(): Double? {
//        return vote_average
//    }
//
//    internal fun setVoteAverage(vote_average: Double?) {
//        this.vote_average = vote_average
//    }
//}
