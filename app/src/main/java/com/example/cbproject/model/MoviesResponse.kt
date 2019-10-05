package com.example.cbproject.model





//class MoviesResponse : Parcelable {
//    @SerializedName("page")
//    var page: Int = 0
//    @SerializedName("results")
//    var results: List<Movie>? = null
//    @SerializedName("total_results")
//    var totalResults: Int = 0
//    @SerializedName("total_pages")
//    var totalPages: Int = 0
//
//   internal fun getPage(): Int {
//        return page
//    }
//
//    internal fun setPage(page: Int) {
//        this.page = page
//    }
//
//    internal fun getResults(): List<Movie>? {
//        return results
//    }
//
//    internal fun getMovies(): List<Movie>? {
//        return results
//    }
//
//    internal fun setResults(results: List<Movie>) {
//        this.results = results
//    }
//
//    internal fun setMovies(results: List<Movie>) {
//        this.results = results
//    }
//
//   internal fun getTotalResults(): Int {
//        return totalResults
//    }
//
//    internal fun setTotalResults(totalResults: Int) {
//        this.totalResults = totalResults
//    }
//
//    internal fun getTotalPages(): Int {
//        return totalPages
//    }
//
//    internal fun setTotalPages(totalPages: Int) {
//        this.totalPages = totalPages
//    }
//
//
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    override fun writeToParcel(dest: Parcel, flags: Int) {
//        dest.writeInt(this.page)
//        dest.writeTypedList(this.results)
//        dest.writeInt(this.totalResults)
//        dest.writeInt(this.totalPages)
//    }
//
//    constructor() {}
//
//    protected constructor(`in`: Parcel) {
//        this.page = `in`.readInt()
//        this.results = `in`.createTypedArrayList(Movie.CREATOR)
//        this.totalResults = `in`.readInt()
//        this.totalPages = `in`.readInt()
//    }
//
//    companion object {
//
//        @SuppressLint("ParcelCreator")
//        val CREATOR= object : Parcelable.Creator<MoviesResponse> {
//            override fun createFromParcel(source: Parcel): MoviesResponse {
//                return MoviesResponse(source)
//            }
//
//            override fun newArray(size: Int): Array<MoviesResponse?> {
//                return arrayOfNulls(size)
//            }
//        }
//    }
//}

data class MoviesResponse(var page: Number?, var total_results: Number?, var total_pages: Number?, var results: List<Movie>?) {
    internal fun getPage(): Number? {
        return page
    }

    internal fun setPage(page: Number) {
        this.page = page
    }

    internal fun getResults(): List<Movie>? {
        return results
    }

    internal fun getMovies(): List<Movie>? {
        return results
    }

    internal fun setResults(results: List<Movie>) {
        this.results = results
    }

    internal fun setMovies(results: List<Movie>) {
        this.results = results
    }

   internal fun getTotalResults(): Number? {
        return total_results
    }

    internal fun setTotalResults(totalResults:Number?) {
        this.total_results = total_results
    }

    internal fun getTotalPages(): Number? {
        return total_pages
    }

    internal fun setTotalPages(totalPages: Number?) {
        this.total_pages = total_pages
    }
}