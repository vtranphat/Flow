package com.flowapp.nzchos.flow

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hendraanggrian.pikasso.picasso

class tagOutfitAdapter (val tagArray: ArrayList<String>, val tagSelected: ArrayList<String>, context: Context) : RecyclerView.Adapter<tagOutfitAdapter.ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return tagArray.size
    }

    // Inflates the item views
    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        var allReadySelected = false
        holder.tagName.text = tagArray[position]
        holder.tagName.setOnClickListener {
            if (allReadySelected == false) {
                tagSelected.add(tagArray[position])
                holder.tagName.setTextColor(Color.parseColor("#ff0000"))
                allReadySelected = true
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tagOutfitAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.tag_list, parent, false)
        return tagOutfitAdapter.ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val tagName: TextView = view.findViewById(R.id.tagName)
    }
}