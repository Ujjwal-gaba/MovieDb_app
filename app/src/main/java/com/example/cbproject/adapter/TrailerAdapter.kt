//package com.example.cbproject.adapter
//
//import android.content.Context
//import android.content.Intent
//import android.net.Uri
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.core.content.ContextCompat.startActivity
//import androidx.recyclerview.widget.RecyclerView
//import com.example.cbproject.R
////import com.example.cbproject.R
//import com.example.cbproject.model.Trailer
//import kotlinx.android.synthetic.main.trailer_card.view.*
//
////import com.example.cbproject.model.Trailer
////import android.R
//
//
//
//class TrailerAdapter( var mContext: Context,  var trailerList: List<Trailer>?) :
//    RecyclerView.Adapter<TrailerAdapter.MyViewHolder>() {
//
//
//
//
//    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
//        val view = LayoutInflater.from(viewGroup.context)
//            .inflate(com.example.cbproject.R.layout.trailer_card, viewGroup, false)
//        return MyViewHolder(view,mContext,trailerList)
//
//    }
//
//    override fun onBindViewHolder(viewHolder: TrailerAdapter.MyViewHolder, i: Int) {
//        viewHolder.title.text = trailerList?.get(i)?.getName()
//
//    }
//
//    override fun getItemCount(): Int {
//
//-     return trailerList!!.size
////return 10
//    }
//
//    class MyViewHolder(view: View,mContext: Context,trailerList: List<Trailer>?) : RecyclerView.ViewHolder(view) {
//        var title: TextView = view.findViewById<View>(R.id.title) as TextView
//       // var thumbnail: ImageView = view.findViewById<View>(com.example.cbproject.R.id.thumbnail) as ImageView
//
//        init {
//
//
//
//            view.setOnClickListener ( object :View.OnClickListener{
//                override fun onClick(p0: View?) {
//                    val pos = adapterPosition
//                    if (pos != RecyclerView.NO_POSITION) {
//                        val clickedDataItem = trailerList?.get(pos)
//                        val videoId = trailerList?.get(pos)?.getKey()
//                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$videoId"))
//                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                        intent.putExtra("VIDEO_ID", videoId)
//                        mContext.startActivity(intent)
//                        Toast.makeText(p0?.context, "You clicked " + clickedDataItem?.name, Toast.LENGTH_SHORT).show()
//                    }
//                }
//                })
//
//                }
//
//
//        }
//}
//





