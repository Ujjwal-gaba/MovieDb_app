//package com.example.cbproject

//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.os.Parcelable
//import android.util.Log
//import android.view.View
//import android.widget.ImageView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.cbproject.adapter.TrailerAdapter
//import com.example.cbproject.api.Client
//import com.example.cbproject.api.Service
//import com.example.cbproject.model.Movie
//import com.example.cbproject.model.Trailer
//import com.example.cbproject.model.TrailerResponse
//import com.github.ivbaranov.mfb.BuildConfig
//import com.google.android.material.appbar.AppBarLayout
//import com.google.android.material.appbar.CollapsingToolbarLayout
//import kotlinx.android.synthetic.main.activity_detail.*
//import kotlinx.android.synthetic.main.activity_detail.view.*
//import kotlinx.android.synthetic.main.content_detail.*
//import kotlinx.android.synthetic.main.content_detail.view.*
//import kotlinx.android.synthetic.main.trailer_card.*
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class DetailActivity : AppCompatActivity() {
//
//    var THE_MOVIE_DB_API_TOKEN="e5a3515630b2f6c99098494f03054864"
//    // var recyclerView: RecyclerView? = null
//    // var adapter: TrailerAdapter? = null
//     var trailerList: List<Trailer>? = null
//    //private var favoriteDbHelper:FavoriteDbHelper? = null
////private var favorite: Movie? = null
//   // val activity = this@DetailActivity
//
//     var movie: Movie? = null
//     var thumbnail: String? = null
//     var movieName: String? = null
//     var synopsis: String? = null
//     var rating: String? = null
//     var dateOfRelease: String? = null
//    var movie_id: Int = 0
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail)
//      //  val toolbar: androidx.appcompat.widget.Toolbar? = null
//       setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//        initCollapsingToolbar()
////        var imageView = findViewById<View>(R.id.thumbnail_image_header) as ImageView
////        var nameOfMovie = findViewById<View>(R.id.title) as TextView
////        var plotSynopsis = findViewById<View>(R.id.plotsynopsis) as TextView
////        var userRating = findViewById<View>(R.id.userrating) as TextView
////        var releaseDate = findViewById<View>(R.id.releasedate) as TextView
//        var intentThatStartedThisActivity=Intent(this,TrailerAdapter::class.java)
//        if (intentThatStartedThisActivity.hasExtra("VIDEO_ID")) {
//
//            movie =  Intent(this,TrailerAdapter::class.java).getParcelableExtra<Parcelable>("VIDEO_ID") as Movie
//
//            thumbnail = movie!!.getPosterPath()
//            movieName = movie!!.getOriginalTitle()
//            synopsis = movie!!.getOverview()
//            rating = (movie!!.getVoteAverage()!!).toString()
//            dateOfRelease = movie!!.getReleaseDate()
//            movie_id = movie!!.getId()!!
//
//            val poster = "https://image.tmdb.org/t/p/w500$thumbnail"
//
//            Glide.with(this)
//                .load(poster)
//                .placeholder(R.drawable.load)
//                .into(imageView)
//
////           tit.text = movieName.toString()
//           plotsynopsis.text = synopsis.toString()
//            userrating.text = rating.toString()
//            releasedate.text = dateOfRelease.toString()
//
//        } else {
//            Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show()
//        }
//
////val materialFavoriteButtonNice = findViewById<View>(R.id.favorite_button) as MaterialFavoriteButton
////
////val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
////
////materialFavoriteButtonNice.setOnFavoriteChangeListener { buttonView, favorite ->
////    if (favorite) {
////        val editor = getSharedPreferences("com.delaroystudios.movieapp.DetailActivity", MODE_PRIVATE).edit()
////        editor.putBoolean("Favorite Added", true)
////        editor.commit()
////        saveFavorite()
////        Snackbar.make(buttonView, "Added to Favorite",
////            Snackbar.LENGTH_SHORT).show()
////    } else {
////        val movie_id = intent.extras!!.getInt("id")
////        favoriteDbHelper = FavoriteDbHelper(this@DetailActivity)
////        favoriteDbHelper!!.deleteFavorite(movie_id)
////
////        val editor = getSharedPreferences("com.delaroystudios.movieapp.DetailActivity", MODE_PRIVATE).edit()
////        editor.putBoolean("Favorite Removed", true)
////        editor.commit()
////        Snackbar.make(buttonView, "Removed from Favorite",
////            Snackbar.LENGTH_SHORT).show()
////    }
////}
//
//        initViews()
//
//    }
//
//    private fun initCollapsingToolbar() {
//      //  val collapsingToolbarLayout : CollapsingToolbarLayout?=null
//        collapsing_toolbar!!.title=""
//     //   val appBarLayout : AppBarLayout?=null
//            appbar.setExpanded(true)
//        appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
//            var isShow = false
//            var scrollRange = -1
//
//            override fun onOffsetChanged(appBar: AppBarLayout, verticalOffset: Int) {
//                if (scrollRange == -1) {
//                    scrollRange = appBar.totalScrollRange
//                }
//                if (scrollRange + verticalOffset == 0) {
//                    collapsing_toolbar.title=getString(R.string.movie_details)
//                    isShow = true
//                } else if (isShow) {
//                    collapsing_toolbar.title=""
//                    isShow = false
//                }
//            }
//        })
//    }
//
//    private fun initViews() {
//        trailerList =ArrayList()
//        var  adapter = TrailerAdapter(this, trailerList!!)
//
//      //  var recyclerView: RecyclerView? = null
//        var mLayoutManager = LinearLayoutManager(applicationContext)
//        recycler_view1.layoutManager = mLayoutManager
//        recycler_view1.adapter = adapter
//        adapter!!.notifyDataSetChanged()
//
//        loadJSON()
//
//    }
//
//    private fun loadJSON() {
//
//        try {
//            if (THE_MOVIE_DB_API_TOKEN.isEmpty()) {
//                Toast.makeText(applicationContext, "Please obtain your API Key from themoviedb.org", Toast.LENGTH_SHORT)
//                    .show()
//                return
//            }
//            val Client = Client()
//            val apiService = Client.getClient()?.create(Service::class.java)
//            val call = apiService?.getMovieTrailer(movie_id,THE_MOVIE_DB_API_TOKEN)
//            call?.enqueue(object : Callback<TrailerResponse> {
//                override fun onResponse(call: Call<TrailerResponse>, response: Response<TrailerResponse>?) {
//                    var trailer = response?.body()?.getResults()
//                    recycler_view1.adapter = TrailerAdapter(applicationContext, trailer)
//                    recycler_view1.smoothScrollToPosition(0)
//                }
//
//                override fun onFailure(call: Call<TrailerResponse>, t: Throwable) {
//                    Log.d("Error", t.message)
//                    Toast.makeText(this@DetailActivity, "Error fetching trailer data", Toast.LENGTH_SHORT).show()
//
//                }
//            })
//
//        } catch (e: Exception) {
//            Log.d("Error", e.message)
//            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
//        }
//
//    }
//
//// fun saveFavorite() {
////favoriteDbHelper = FavoriteDbHelper(activity)
////favorite = Movie()
////
////val rate = movie!!.getVoteAverage()
////
////
////favorite!!.setId(movie_id)
////favorite!!.setOriginalTitle(movieName)
////favorite!!.setPosterPath(thumbnail)
////favorite!!.setVoteAverage(rate)
////favorite!!.setOverview(synopsis)
////
////favoriteDbHelper!!.addFavorite(favorite)
////}
//
//
//}