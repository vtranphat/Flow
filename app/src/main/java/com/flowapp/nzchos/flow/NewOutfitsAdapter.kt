package com.flowapp.nzchos.flow

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item.view.*

class NewOutfitsAdapter(val posts: ArrayList<String>) : RecyclerView.Adapter<NewOutfitsAdapter.ViewHolder>() {


    override fun getItemCount() = posts.size


    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.weshBoo.text = posts[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.new_outfits_list, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val weshBoo : TextView = itemView.findViewById(R.id.weshBoo)
    }
}