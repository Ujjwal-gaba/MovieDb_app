package com.example.cbproject.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cbproject.Detail3Activity
import com.example.cbproject.R
import com.example.cbproject.model.Movie

class MoviesAdapter(private val mContext: Context, var movieList: List<Movie>?) :
    RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.movie_card, viewGroup, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder:MyViewHolder, i: Int) {
        viewHolder.title.text = movieList?.get(i)?.getOriginalTitle()
        val vote = (movieList?.get(i)?.getVoteAverage()).toString()
        viewHolder.userrating.text = vote

        val poster = "https://image.tmdb.org/t/p/w500" + movieList?.get(i)?.getPosterPath()

        Glide.with(mContext)
            .load(poster)
            .placeholder(R.drawable.load)
            .into(viewHolder.thumbnail)

    }

//    internal fun setMovies(movie : List<Movie>) {
//              movieList = movie
//            notifyDataSetChanged()
//        }

    override fun getItemCount(): Int {
        return movieList!!.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var userrating: TextView
        var thumbnail: ImageView

        init {
            title = view.findViewById<View>(R.id.title) as TextView
            userrating = view.findViewById<View>(R.id.userrating) as TextView
            thumbnail = view.findViewById<View>(R.id.thumbnail) as ImageView

            view.setOnClickListener { v ->
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val clickedDataItem:Movie? = movieList?.get(pos)
                    val intent = Intent(mContext, Detail3Activity::class.java)
                    intent.putExtra("movies", clickedDataItem)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    mContext.startActivity(intent)
                    Toast.makeText(v.context, "You clicked " + clickedDataItem?.getOriginalTitle(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
