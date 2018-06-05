package com.flowapp.nzchos.flow

import android.bluetooth.le.AdvertisingSetCallback
import android.os.Bundle
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout.VERTICAL
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_tab_best.*


class TabBestFragment : Fragment() {


    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab_best, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Retrieve data firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("posts")


        val tagListener = object : ValueEventListener {


            override fun onDataChange(tagSnapshot: DataSnapshot) {
                if (tagSnapshot.exists()) {
                    val message = tagSnapshot.getValue(Message::class.java)
                    println(tagSnapshot)

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Failed to read value
            }
        }

        mDatabase.orderByChild("day").equalTo(true).addValueEventListener(tagListener)


        // Create RecyclerView
        val posts: ArrayList<String> = ArrayList()

        for (i in 1..100) {
            posts.add("Post # $i")
        }

        newOutfitsRecycler.layoutManager = GridLayoutManager(activity?.applicationContext, 2, VERTICAL,  false)
        newOutfitsRecycler.adapter = NewOutfitsAdapter(posts)
    }

}