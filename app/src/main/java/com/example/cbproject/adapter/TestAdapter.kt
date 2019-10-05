package com.example.cbproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cbproject.R
import com.example.cbproject.model.Movie

import java.util.ArrayList

class TestAdapter(private val layoutInflater: LayoutInflater) : RecyclerView.Adapter<TestAdapter.MyViewHolder>() {

    private val movie: MutableList<Movie>


    init {
        this.movie = ArrayList()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(layoutInflater.inflate(R.layout.movie_card, parent, false))
    }


    fun getMovie(): List<Movie> {
        return movie
    }

    fun setMovie(movieList: List<Movie>) {
        this.movie.clear()
        this.movie.addAll(movieList)
        notifyItemRangeInserted(0, movie.size)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }


    override fun getItemCount(): Int {
        return movie.size

    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var userrating: TextView
        internal var image: ImageView? = null

        init {
            title = view.findViewById<View>(R.id.title) as TextView
            userrating = view.findViewById<View>(R.id.userrating) as TextView
            view.setOnClickListener { }
        }
    }
}
