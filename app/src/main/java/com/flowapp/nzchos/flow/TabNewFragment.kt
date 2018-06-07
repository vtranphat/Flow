package com.flowapp.nzchos.flow

import android.os.Bundle
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout.VERTICAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_tab_best.*
import kotlinx.android.synthetic.main.fragment_tab_new.*


class TabNewFragment : Fragment() {

    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase: DatabaseReference


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_tab_new, container, false)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Retrieve data firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("posts")
        println("SUUUUS")

        val tagListener = object : ValueEventListener {
            val best_posts: ArrayList<String> = ArrayList()
            val likeArray: ArrayList<Long> = ArrayList()
            val photoArray: ArrayList<String> = ArrayList()
            val idArray: ArrayList<String> = ArrayList()

            override fun onDataChange(tagSnapshot: DataSnapshot) {
                if (tagSnapshot.exists()) {

                    val message = tagSnapshot.getValue(Message::class.java)

                    for (snapshot in tagSnapshot.children) {
                        val snapContent: ArrayList<String> = ArrayList()
                        val name = snapshot.value as HashMap<String, String>
                        val bestOutfitArray: ArrayList<String> = ArrayList()
                        val photo = name["photoUrl"] as String
                        val numberOfLike = name["rate"] as Long
                        val postId = snapshot.key as String


                        //bestOutfitArray.add(name)

                        //println("yo" + bestArray)
                        // Create RecyclerView
                        likeArray.add(numberOfLike)
                        best_posts.add(postId)
                        idArray.add(postId)
                        photoArray.add(photo)


                        bestOutfitsRecycler.layoutManager = GridLayoutManager(activity?.applicationContext, 2, VERTICAL, false)
                        bestOutfitsRecycler.adapter = BestOutfitsAdapter(best_posts, photoArray, likeArray, idArray)
                        println("PPPPPPPPP")

                    }

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Failed to read value
            }

        }
        mDatabase.orderByChild("toShow").equalTo(true).addValueEventListener(tagListener)
        //mDatabase.addValueEventListener(tagListener)
    }
}