//package com.example.cbproject
//
//import android.app.Activity
//
////import android.arch.lifecycle.Observer;
////import android.arch.lifecycle.ViewModelProviders;
//import android.content.Context
//import android.content.ContextWrapper
//import android.content.Intent
//import android.content.SharedPreferences
//import android.content.res.Configuration
//import android.net.ConnectivityManager
//import android.net.NetworkInfo
//import android.os.AsyncTask
//import android.os.Build
//import android.os.Parcelable
//import android.preference.PreferenceManager
////import android.support.annotation.Nullable;
////import android.support.v4.widget.SwipeRefreshLayout;
////import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle
////import android.support.v7.widget.DefaultItemAnimator;
////import android.support.v7.widget.GridLayoutManager;
////import android.support.v7.widget.RecyclerView;
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.Menu
//import android.view.MenuItem
//import android.widget.Toast
//
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.DefaultItemAnimator
//import androidx.recyclerview.widget.GridLayoutManager
//import androidx.recyclerview.widget.RecyclerView
////import com.delaroystudios.movieapp.ViewModel.MainViewModel;
////import com.delaroystudios.movieapp.adapter.MoviesAdapter;
////import com.delaroystudios.movieapp.adapter.TestAdapter;
////import com.delaroystudios.movieapp.api.Client;
////import com.delaroystudios.movieapp.api.Service;
////import com.delaroystudios.movieapp.data.FavoriteDbHelper;
////import com.delaroystudios.movieapp.database.FavoriteEntry;
////import com.delaroystudios.movieapp.model.Movie;
////import com.delaroystudios.movieapp.model.MoviesResponse;
//
//import java.io.IOException
//import java.util.ArrayList
//import java.util.Collections
//
//import com.example.cbproject.adapter.MoviesAdapter
//import com.example.cbproject.api.Client
//import com.example.cbproject.api.Service
//import com.example.cbproject.model.Movie
//import com.example.cbproject.model.MoviesResponse
//import okhttp3.Cache
//import okhttp3.Interceptor
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//import android.R.attr.theme
//import android.R.attr.x
//import android.app.ProgressDialog
//import android.view.View
//import java.lang.System.`in`
//
//@Suppress("DEPRECATION")
//class Main2Activity : AppCompatActivity() {
//
//    private var recyclerView: RecyclerView? = null
//    private var adapter: MoviesAdapter? = null
//    internal var pd: ProgressDialog? = null
//    private val activity = this@Main2Activity
//    private var savedRecyclerLayoutState: Parcelable? = null
//    private var moviesInstance: ArrayList<Movie>? = ArrayList()
//
//    private val isNetworkAvailable: Boolean
//        get() {
//            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//            val activeNetworkInfo = connectivityManager.activeNetworkInfo
//            return activeNetworkInfo != null && activeNetworkInfo.isConnected
//        }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        if (savedInstanceState != null) {
//            moviesInstance = savedInstanceState.getParcelableArrayList(LIST_STATE)
//            savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT)
//            displayData()
//        } else {
//            initViews()
//        }
//    }
//
//    private fun displayData() {
//        var recyclerView :RecyclerView?=null
//        adapter = MoviesAdapter(this, moviesInstance)
//        if (getActivity()?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            recyclerView!!.layoutManager = GridLayoutManager(this, 2)
//        } else {
//            recyclerView!!.layoutManager = GridLayoutManager(this, 4)
//        }
//        recyclerView!!.itemAnimator = DefaultItemAnimator()
//        recyclerView!!.adapter = adapter
//        restoreLayoutManagerPosition()
//        adapter!!.notifyDataSetChanged()
//    }
//
//    private fun restoreLayoutManagerPosition() {
//        if (savedRecyclerLayoutState != null) {
//            recyclerView!!.layoutManager!!.onRestoreInstanceState(savedRecyclerLayoutState)
//        }
//    }
//
//    private fun initViews() {
//        var recyclerView :RecyclerView?= null
//        if (getActivity()?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            recyclerView!!.layoutManager = GridLayoutManager(this, 2)
//        } else {
//            recyclerView!!.layoutManager = GridLayoutManager(this, 4)
//        }
//
//        recyclerView!!.itemAnimator = DefaultItemAnimator()
//        recyclerView!!.adapter = adapter
//        loadJSON()
//    }
//
//    //    private void initViews2(){
//    //        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//    //        getAllFavorite();
//    //    }
//
//    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
//        super.onSaveInstanceState(savedInstanceState)
//        savedInstanceState.putParcelableArrayList(LIST_STATE, moviesInstance)
//        savedInstanceState.putParcelable(BUNDLE_RECYCLER_LAYOUT, recyclerView!!.layoutManager!!.onSaveInstanceState())
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        moviesInstance = savedInstanceState.getParcelableArrayList(LIST_STATE)
//        savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT)
//        super.onRestoreInstanceState(savedInstanceState)
//    }
//
//
//    fun getActivity(): Activity? {
//        var context: Context = this
//        while (context is ContextWrapper) {
//            if (context is Activity) {
//                return context
//            }
//            context = context.baseContext
//        }
//        return null
//
//    }
//
//    private fun loadJSON() {
//        val sortOrder = checkSortOrder()
//
//        if (sortOrder == this.getString(R.string.pref_most_popular)) {
//
//            try {
//                if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
//                    Toast.makeText(
//                        applicationContext,
//                        "Please obtain API Key firstly from themoviedb.org",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    pd!!.dismiss()
//                    return
//                }
//
//                val Client = Client()
//                val apiService = Client?.getClient()?.create(Service::class.java)
//                val call = apiService?.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN)
//                call?.enqueue(object : Callback<MoviesResponse> {
//                    override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
//                        if (response.isSuccessful) {
//                            Toast.makeText(
//                                this@Main2Activity,
//                                "the value is " + moviesInstance!!.size,
//                                Toast.LENGTH_SHORT
//                            ).show()
//
//                            if (response.body() != null) {
//                                val movies = (response.body().results)
//                                //moviesInstance.clear();
//                                moviesInstance!!.addAll(movies!!)
//                                recyclerView!!.adapter = MoviesAdapter(applicationContext, movies)
//                                recyclerView!!.smoothScrollToPosition(0)
//                            }
//                        }
//                    }
//
//                    override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
//                        Log.d("Error", t.message)
//                        Toast.makeText(this@Main2Activity, "Error Fetching Data!", Toast.LENGTH_SHORT).show()
//
//                    }
//                })
//            } catch (e: Exception) {
//                Log.d("Error", e.message)
//                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
//            }
//
//        } else if (sortOrder == this.getString(R.string.favorite)) {
//            //            initViews2();
//        } else {
//
//            try {
//                if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
//                    Toast.makeText(
//                        applicationContext,
//                        "Please obtain API Key firstly from themoviedb.org",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    pd!!.dismiss()
//                    return
//                }
//
//                val Client = Client()
//                val apiService = Client.getClient()?.create(Service::class.java)
//                val call = apiService?.getTopRatedMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN)
//                call?.enqueue(object : Callback<MoviesResponse> {
//                    override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
//                        val movies = response.body().results
//                        //moviesInstance.clear();
//                        moviesInstance!!.addAll(movies!!)
//                        recyclerView!!.adapter = MoviesAdapter(applicationContext, movies)
//                        recyclerView!!.smoothScrollToPosition(0)
//                    }
//
//                    override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
//                        Log.d("Error", t.message)
//                        Toast.makeText(this@Main2Activity, "Error Fetching Data!", Toast.LENGTH_SHORT).show()
//
//                    }
//                })
//            } catch (e: Exception) {
//                Log.d("Error", e.message)
//                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
//            }
//
//        }
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.menu_settings -> {
//                val intent = Intent(this, SettingsActivity::class.java)
//                startActivity(intent)
//                return true
//            }
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }
//
//    private fun checkSortOrder(): String? {
//        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
//
//        return preferences.getString(
//            getString(R.string.pref_sort_order_key),
//            getString(R.string.pref_most_popular)
//        )
//    }
//
//    companion object {
//        val LOG_TAG = MoviesAdapter::class.java.name
//
//        private val LIST_STATE = "list_state"
//        private val BUNDLE_RECYCLER_LAYOUT = "recycler_layout"
//    }
//
//    //    private void getAllFavorite(){
//    //
//    //        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
//    //        viewModel.getFavorite().observe(this, new Observer<List<FavoriteEntry>>() {
//    //            @Override
//    //            public void onChanged(@Nullable List<FavoriteEntry> imageEntries) {
//    //                List<Movie> movies = new ArrayList<>();
//    //                for (FavoriteEntry entry : imageEntries){
//    //                    Movie movie = new Movie();
//    //                    movie.setId(entry.getMovieid());
//    //                    movie.setOverview(entry.getOverview());
//    //                    movie.setOriginalTitle(entry.getTitle());
//    //                    movie.setPosterPath(entry.getPosterpath());
//    //                    movie.setVoteAverage(entry.getUserrating());
//    //
//    //                    movies.add(movie);
//    //                }
//    //
//    //                adapter.setMovies(movies);
//    //            }
//    //        });
//    //    }
//}