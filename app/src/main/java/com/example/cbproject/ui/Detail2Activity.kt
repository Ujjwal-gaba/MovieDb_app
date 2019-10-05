package com.example.cbproject.ui

//import android.content.Intent
//import android.database.sqlite.SQLiteDatabase
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.ImageView
//import android.widget.TextView
//import android.widget.Toast
//import android.widget.Toolbar
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.cbproject.BuildConfig
//import com.example.cbproject.R
//import com.example.cbproject.adapter.ReviewAdapter
//import com.example.cbproject.adapter.TrailerAdapter
//import com.example.cbproject.api.Client
//import com.example.cbproject.api.Service
//import com.example.cbproject.model.*
//import com.github.ivbaranov.mfb.MaterialFavoriteButton
//import com.google.android.material.appbar.CollapsingToolbarLayout
//import com.google.android.material.snackbar.Snackbar
//import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//import java.util.ArrayList
//
//class Detail2Activity : AppCompatActivity() {
//    internal var nameOfMovie: TextView? = null
//    internal var plotSynopsis: TextView? = null
//    internal var userRating: TextView? = null
//    internal var releaseDate: TextView? = null
//    internal var imageView: ImageView? = null
//    private var recyclerView: RecyclerView? = null
//    private var adapter: TrailerAdapter? = null
//    private var trailerList: List<Trailer>? = null
//    //    private FavoriteDbHelper favoriteDbHelper;
//    private val favorite: Movie? = null
//    private val activity = this@Detail2Activity
//    private val mDb: SQLiteDatabase? = null
//
//    internal var movie: Movie? = null
//    internal var thumbnail: String? = null
//    internal var movieName: String? = null
//    internal var synopsis: String? = null
//    internal var rating: String?=null
//    internal var dateOfRelease: String? = null
//    internal var movie_id: Int = 0
//
//    public override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail)
//        val toolbar :androidx.appcompat.widget.Toolbar?=null
//        setSupportActionBar(toolbar)
//
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//
//        //TODO
//        //        FavoriteDbHelper dbHelper = new FavoriteDbHelper(this);
//        //        mDb = dbHelper.getWritableDatabase();
//
//
////        imageView = findViewById<View>(R.id.thumbnail_image_header)
////        // nameOfMovie = (TextView) findViewById(R.id.title);
////        plotSynopsis = findViewById<View>(R.id.plotsynopsis)
////        userRating = findViewById<View>(R.id.userrating)
////        releaseDate = findViewById<View>(R.id.releasedate)
//
//        val intentThatStartedThisActivity = intent
//        if (intentThatStartedThisActivity.hasExtra("movies")) {
//
//            movie = intent.getParcelableExtra("movies")
//
//            thumbnail = movie!!.posterPath
//            movieName = movie!!.originalTitle
//            synopsis = movie!!.overview
//            rating = java.lang.Double.toString(movie!!.voteAverage!!)
//            dateOfRelease = movie!!.releaseDate
//            movie_id = movie!!.id!!
//
//            val poster = "https://image.tmdb.org/t/p/w500" + thumbnail!!
//
//            Glide.with(this)
//                .load(poster)
//                .placeholder(R.drawable.load)
//                .into(imageView)
//
//            //  nameOfMovie.setText(movieName);
//            plotSynopsis?.text = synopsis
//            userRating?.text = rating
//            releaseDate?.text = dateOfRelease
//
//            //TODO
//            (findViewById<View>(R.id.collapsing_toolbar) as CollapsingToolbarLayout).title = movieName
//
//        } else {
//            Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show()
//        }
//
//        val materialFavoriteButton = findViewById<View>(R.id.favorite_button) as MaterialFavoriteButton
//
//        //        if (Exists(movieName)){
//        //            materialFavoriteButton.setFavorite(true);
//        //            materialFavoriteButton.setOnFavoriteChangeListener(
//        //                    new MaterialFavoriteButton.OnFavoriteChangeListener() {
//        //                        @Override
//        //                        public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
//        //                            if (favorite == true) {
//        ////                                saveFavorite();
//        //                                Snackbar.make(buttonView, "Added to Favorite",
//        //                                        Snackbar.LENGTH_SHORT).show();
//        //                            } else {
//        ////                                favoriteDbHelper = new FavoriteDbHelper(DetailActivity.this);
//        ////                                favoriteDbHelper.deleteFavorite(movie_id);
//        //                                Snackbar.make(buttonView, "Removed from Favorite",
//        //                                        Snackbar.LENGTH_SHORT).show();
//        //                            }
//        //                        }
//        //                    });
//        //
//
//        //        }else {
//        materialFavoriteButton.setOnFavoriteChangeListener { buttonView, favorite ->
//            if (favorite == true) {
//                //                                saveFavorite();
//                Snackbar.make(
//                    buttonView, "Added to Favorite",
//                    Snackbar.LENGTH_SHORT
//                ).show()
//            } else {
//                val movie_id = intent.extras!!.getInt("id")
//                //                                favoriteDbHelper = new FavoriteDbHelper(DetailActivity.this);
//                //                                favoriteDbHelper.deleteFavorite(movie_id);
//                Snackbar.make(
//                    buttonView, "Removed from Favorite",
//                    Snackbar.LENGTH_SHORT
//                ).show()
//            }
//        }
//
//
//        //        }
//
//        initViews()
//
//    }
//
//    //    public boolean Exists(String searchItem) {
//    //
//    //        String[] projection = {
//    //                FavoriteContract.FavoriteEntry._ID,
//    //                FavoriteContract.FavoriteEntry.COLUMN_MOVIEID,
//    //                FavoriteContract.FavoriteEntry.COLUMN_TITLE,
//    //                FavoriteContract.FavoriteEntry.COLUMN_USERRATING,
//    //                FavoriteContract.FavoriteEntry.COLUMN_POSTER_PATH,
//    //                FavoriteContract.FavoriteEntry.COLUMN_PLOT_SYNOPSIS
//    //
//    //        };
//    //        String selection = FavoriteContract.FavoriteEntry.COLUMN_TITLE + " =?";
//    //        String[] selectionArgs = { searchItem };
//    //        String limit = "1";
//    //
//    //        Cursor cursor = mDb.query(FavoriteContract.FavoriteEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null, limit);
//    //        boolean exists = (cursor.getCount() > 0);
//    //        cursor.close();
//    //        return exists;
//    //    }
//    //
//
//    private fun initViews() {
//        trailerList = ArrayList()
//        adapter = TrailerAdapter(this, trailerList!!)
//
////        recyclerView = findViewById<View>(R.id.recycler_view1)
//        val mLayoutManager = LinearLayoutManager(applicationContext)
//        recyclerView!!.layoutManager = mLayoutManager
//        recyclerView!!.adapter = adapter
//        adapter!!.notifyDataSetChanged()
//
//        loadJSON()
//        loadReview()
//    }
//
//    private fun loadJSON() {
//        try {
//            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
//                Toast.makeText(applicationContext, "Please get your API Key", Toast.LENGTH_SHORT).show()
//                return
//            } else {
//                val Client = Client()
//                val apiService = Client.getClient()?.create(Service::class.java)
//                val call = apiService?.getMovieTrailer(movie_id, BuildConfig.THE_MOVIE_DB_API_TOKEN)
//                call?.enqueue(object : Callback<TrailerResponse> {
//                    override fun onResponse(call: Call<TrailerResponse>, response: Response<TrailerResponse>) {
//                        if (response.isSuccessful) {
//                            if (response.body() != null) {
//                                val trailer = response.body().results
//                                val recyclerView = findViewById<View>(R.id.recycler_view1) as MultiSnapRecyclerView
//                                val firstManager =
//                                    LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
//                                recyclerView.layoutManager = firstManager
//                                recyclerView.adapter = TrailerAdapter(applicationContext, trailer!!)
//                                recyclerView.smoothScrollToPosition(0)
//                            }
//                        }
//                    }
//
//                    override fun onFailure(call: Call<TrailerResponse>, t: Throwable) {
//                        Log.d("Error", t.message)
//                        Toast.makeText(this@Detail2Activity, "Error fetching trailer", Toast.LENGTH_SHORT).show()
//
//                    }
//                })
//            }
//
//        } catch (e: Exception) {
//            Log.d("Error", e.message)
//            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
//        }
//
//    }
//
//
//    //TODO
//    private fun loadReview() {
//        try {
//            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
//                Toast.makeText(applicationContext, "Please get your API Key", Toast.LENGTH_SHORT).show()
//                return
//            } else {
//                val Client = Client()
//                val apiService = Client.getClient()?.create(Service::class.java)
//                val call = apiService?.getReview(movie_id, BuildConfig.THE_MOVIE_DB_API_TOKEN)
//
//                call?.enqueue(object : Callback<Review> {
//                    override fun onResponse(call: Call<Review>, response: Response<Review>) {
//                        if (response.isSuccessful) {
//                            if (response.body() != null) {
//                                val reviewResults = response.body().results
////                                val recyclerView2 = findViewById(R.id.recycler_view) as MultiSnapRecyclerView
//                                val recyclerView2:MultiSnapRecyclerView?=null
//                                val firstManager =
//                                    LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
//                                recyclerView2?.layoutManager = firstManager
//                                recyclerView2?.adapter = ReviewAdapter(applicationContext, reviewResults!!)
//                                recyclerView2?.smoothScrollToPosition(0)
//                            }
//                        }
//                    }
//
//                    override fun onFailure(call: Call<Review>, t: Throwable) {
//
//                    }
//                })
//            }
//
//        } catch (e: Exception) {
//            Log.d("Error", e.message)
//            Toast.makeText(this, "unable to fetch data", Toast.LENGTH_SHORT).show()
//        }
//
//    }
//
//    //    public void saveFavorite(){
//    //        favoriteDbHelper = new FavoriteDbHelper(activity);
//    //        favorite = new Movie();
//    //
//    //        Double rate = movie.getVoteAverage();
//    //
//    //
//    //        favorite.setId(movie_id);
//    //        favorite.setOriginalTitle(movieName);
//    //        favorite.setPosterPath(thumbnail);
//    //        favorite.setVoteAverage(rate);
//    //        favorite.setOverview(synopsis);
//    //
//    //        favoriteDbHelper.addFavorite(favorite);
//    //    }
//    //
//    //    @Override
//    //    public boolean onCreateOptionsMenu(Menu menu) {
//    //
//    //        getMenuInflater().inflate(R.menu.detail_menu, menu);
//    //        return true;
//    //    }
//    //
//    //    @Override
//    //    public boolean onOptionsItemSelected(MenuItem item) {
//    //
//    //        switch (item.getItemId()) {
//    //            case R.id.share:
//    //                shareContent();
//    //
//    //                return true;
//    //        }
//    //
//    //        return super.onOptionsItemSelected(item);
//    //    }
//    //
//    //    private void shareContent(){
//    //
//    //        Bitmap bitmap =getBitmapFromView(imageView);
//    //        try {
//    //            File file = new File(this.getExternalCacheDir(),"logicchip.png");
//    //            FileOutputStream fOut = new FileOutputStream(file);
//    //            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
//    //            fOut.flush();
//    //            fOut.close();
//    //            file.setReadable(true, false);
//    //            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
//    //            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//    //            intent.putExtra(Intent.EXTRA_TEXT, movieName);
//    //            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
//    //            intent.setType("image/png");
//    //            startActivity(Intent.createChooser(intent, "Share image via"));
//    //        } catch (Exception e) {
//    //            e.printStackTrace();
//    //        }
//    //
//    //    }
//    //
//    //    private Bitmap getBitmapFromView(View view) {
//    //        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
//    //        Canvas canvas = new Canvas(returnedBitmap);
//    //        Drawable bgDrawable =view.getBackground();
//    //        if (bgDrawable!=null) {
//    //            bgDrawable.draw(canvas);
//    //        }   else{
//    //            canvas.drawColor(Color.WHITE);
//    //        }
//    //        view.draw(canvas);
//    //        return returnedBitmap;
//    //    }
//
//
//}