

package com.example.cbproject

//import android.app.Activity
//import android.app.ProgressDialog
//import android.content.Context
//import android.content.ContextWrapper
//import android.content.Intent
//import android.content.SharedPreferences
//import android.content.res.Configuration

//import android.app.ProgressDialog
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.AsyncTask
import android.os.Bundle
//import android.os.Bundle

import android.util.Log
//import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

//import android.os.Bundle
//import android.preference.PreferenceManager
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.Menu
//import android.view.MenuItem
//import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cbproject.adapter.MoviesAdapter
import com.example.cbproject.api.Client
import com.example.cbproject.api.Service
import com.example.cbproject.data.FavoriteDbHelper
import com.example.cbproject.model.Movie

import com.example.cbproject.model.MoviesResponse
import kotlinx.android.synthetic.main.activity_main.main_content
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(),SharedPreferences.OnSharedPreferenceChangeListener {
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, s: String?) {
        Log.d(LOG_TAG, "Preferences updated")
        checkSortOrder()
    }

    //  private var recyclerView: RecyclerView? = null
    private var adapter: MoviesAdapter? = null
    private var movieList: List<Movie>?=null
    internal var pd: AlertDialog? = null
    var THE_MOVIE_DB_API_TOKEN="e5a3515630b2f6c99098494f03054864"// progressdialog is used
  //  private var swipeContainer: SwipeRefreshLayout? = null
    // private var favoriteDbHelper: FavoriteDbHelper? = null
    private val activity = this@MainActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()


//        recyclerView = findViewById(R.id.) as RecyclerView

        //For testing the recipe collection sorting alphabetically
//        val testAdapter = TestAdapter(LayoutInflater.from(this))
//        recyclerView!!.adapter = testAdapter
//        testAdapter.setMovie(movieList!!)


    }

    fun getActivity(): Activity? {
        var context: Context = this
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = (context).baseContext
        }
        return null

    }

    private fun initViews() {

//       var recyclerView : RecyclerView

        movieList = ArrayList()
        adapter = MoviesAdapter(this, movieList)



        if (getActivity()?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recycler_view?.layoutManager=GridLayoutManager(this, 2)
        } else {
            recycler_view?.layoutManager = GridLayoutManager(this, 4)
        }

        recycler_view?.itemAnimator = DefaultItemAnimator()
        recycler_view?.adapter = adapter
        adapter?.notifyDataSetChanged()
         var  favoriteDbHelper = FavoriteDbHelper(activity)
        getAllFavorite(favoriteDbHelper)



       // var  swipeContainer : SwipeRefreshLayout?=null
        main_content!!.setColorSchemeResources(android.R.color.holo_orange_dark)
        main_content!!.setOnRefreshListener {
            initViews()
            Toast.makeText(this@MainActivity, "Movies Refreshed", Toast.LENGTH_SHORT).show()
        }

        checkSortOrder()

    }

    private fun initViews2() {


        movieList = ArrayList()
        adapter = MoviesAdapter(this, movieList)

        if (getActivity()?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recycler_view!!.layoutManager = GridLayoutManager(this, 2)
        } else {
            recycler_view!!.layoutManager = GridLayoutManager(this, 4)
        }

        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.adapter = adapter
        adapter!!.notifyDataSetChanged()
        var favoriteDbHelper = FavoriteDbHelper(activity)


        getAllFavorite(favoriteDbHelper)
    }

    private fun loadJSON() {

        try {
            if (THE_MOVIE_DB_API_TOKEN.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Please obtain API Key firstly from themoviedb.org",
                    Toast.LENGTH_SHORT
                ).show()
                pd!!.dismiss()
                return
            }

            val Client = Client()
            val apiService = Client.getClient()?.create(Service::class.java)
            val call = apiService?.getPopularMovies(THE_MOVIE_DB_API_TOKEN)
            call?.enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    val movies = response.body()?.getResults()
//                     Collections.sort(movies, Movie.BY_NAME_ALPHABETICAL)
                    recycler_view!!.adapter = MoviesAdapter(applicationContext, movies)
                    recycler_view!!.smoothScrollToPosition(0)
                    if (main_content!!.isRefreshing) {
                        main_content!!.isRefreshing = false
                    }

                }

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.d("Error", t.message)
                    Toast.makeText(this@MainActivity, "Error Fetching Data!", Toast.LENGTH_SHORT).show()

                }
            })
        } catch (e: Exception) {
            Log.d("Error", e.message)
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }

    }

    private fun loadJSON1() {

        try {
            if (THE_MOVIE_DB_API_TOKEN.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Please obtain API Key firstly from themoviedb.org",
                    Toast.LENGTH_SHORT
                ).show()
                pd!!.dismiss()
                return
            }

            val Client = Client()
            val apiService = Client.getClient()?.create(Service::class.java)
            val call = apiService?.getTopRatedMovies(THE_MOVIE_DB_API_TOKEN)
            call?.enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    val movies = response.body()?.getResults()
                    recycler_view!!.adapter = MoviesAdapter(applicationContext, movies)
                    recycler_view!!.smoothScrollToPosition(0)
                    if (main_content!!.isRefreshing) {
                        main_content!!.isRefreshing = false
                    }
                }

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.d("Error", t.message)
                    Toast.makeText(this@MainActivity, "Error Fetching Data!", Toast.LENGTH_SHORT).show()

                }
            })
        } catch (e: Exception) {
            Log.d("Error", e.message)
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            else ->return  super.onOptionsItemSelected(item)
        }
    }


    private fun checkSortOrder() {
        val preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
        val sortOrder = preferences.getString(
            this.getString(R.string.pref_sort_order_key),
            this.getString(R.string.pref_most_popular)
        )
        if (sortOrder == this.getString(R.string.pref_most_popular)) {
            Log.d(LOG_TAG, "Sorting by most popular")
            loadJSON()
        } else if (sortOrder == this.getString(R.string.favorite)) {
            Log.d(LOG_TAG, "Sorting by favorite")
            initViews2()
        } else {
            Log.d(LOG_TAG, "Sorting by vote average")
            loadJSON1()
        }
    }

    public override fun onResume() {
        super.onResume()
        if (movieList!!.isEmpty()) {
            checkSortOrder()
        } else {

            checkSortOrder()
        }
    }

    private fun getAllFavorite( favoriteDbHelper: FavoriteDbHelper?): AsyncTask<Void, Void, Void>? {


            class mytask : AsyncTask<Void, Void, Void>() {
                var mutableList=movieList?.toMutableList()
               override fun doInBackground(vararg params: Void): Void? {
                   mutableList?.clear()
                   mutableList?.addAll(favoriteDbHelper!!.getAllFavorite())
                   movieList=mutableList?.toList()
                   return null
               }

               override fun onPostExecute(aVoid: Void?) {
                   super.onPostExecute(aVoid)
                   adapter!!.notifyDataSetChanged()
               }
           }

        return mytask().execute()
   }

    companion object {
        val LOG_TAG = MoviesAdapter::class.java.name
    }

    }

