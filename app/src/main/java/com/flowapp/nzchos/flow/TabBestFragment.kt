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
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class TabBestFragment : Fragment() {


    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference
    var allReadyLoad = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab_best, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Retrieve data firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("posts")


        val tagListener = object : ValueEventListener {
            val posts: ArrayList<String> = ArrayList()
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
                        posts.add(postId)
                        idArray.add(postId)
                        photoArray.add(photo)



                        if (allReadyLoad == false) {
                            allReadyLoad = true
                            newOutfitsRecycler.layoutManager = GridLayoutManager(activity?.applicationContext, 2, VERTICAL,  false)
                            newOutfitsRecycler.adapter = NewOutfitsAdapter(posts, photoArray, likeArray, idArray  )
                        }




                    }

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Failed to read value
            }
        }

        mDatabase.orderByChild("new").equalTo(true).addValueEventListener(tagListener)
        //mDatabase.addValueEventListener(tagListener)


    }

}