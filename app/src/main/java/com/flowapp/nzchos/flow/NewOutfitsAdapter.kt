package com.flowapp.nzchos.flow

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.graphics.BitmapFactory
import com.hendraanggrian.pikasso.picasso
import com.squareup.picasso.Picasso
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class NewOutfitsAdapter(val posts: ArrayList<String>, val photo: ArrayList<String>, val numberOfLike: ArrayList<Long>, val identifantPost: ArrayList<String>) : RecyclerView.Adapter<NewOutfitsAdapter.ViewHolder>() {


    /*val imgOutfit : ImageView= itemView.findViewById(R.id.img_like)
    mImageOutFit = findViewById(R.id.img_like)*/
    override fun getItemCount() = posts.count()


    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.weshBoo.text = posts[position]
        holder.userLike.text = numberOfLike[position].toString(10)
        //holder.imgOutfit.setImageBitmap()
        println("LIKE FDP" + numberOfLike)
        picasso.load(photo[position]).into(holder.imgOutfit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.new_outfits_list, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val weshBoo : TextView = itemView.findViewById(R.id.weshBoo)
        val userLike : TextView = itemView.findViewById(R.id.user_like)
        val imgOutfit : ImageView= itemView.findViewById(R.id.photoView)
    }
}