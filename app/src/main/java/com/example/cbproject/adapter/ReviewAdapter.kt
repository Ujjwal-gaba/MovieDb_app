package com.example.cbproject.adapter

import android.content.Context
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cbproject.R
import com.example.cbproject.model.ReviewResult

class ReviewAdapter(private val mContext: Context, private val reviewResults: List<ReviewResult>) :
    RecyclerView.Adapter<ReviewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {

        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.review_card, viewGroup, false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, i: Int) {
        viewHolder.title.text = reviewResults[i].author
        val url = reviewResults[i].url
        viewHolder.url.text = url
        Linkify.addLinks(viewHolder.url, Linkify.WEB_URLS)

    }

    override fun getItemCount(): Int {
        return reviewResults.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var url: TextView

        init {
            title = view.findViewById<View>(R.id.review_author) as TextView
            url = view.findViewById<View>(R.id.review_link) as TextView

            view.setOnClickListener { val pos = adapterPosition }
        }
    }

}
