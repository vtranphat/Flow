package com.flowapp.nzchos.flow

import android.graphics.Bitmap
import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.EditText
import com.flowapp.nzchos.flow.R.id.photoImageView

import com.flowapp.nzchos.flow.R.styleable.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_tag.*
import org.jetbrains.anko.toolbar

class TagActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference
    lateinit var tagList : MutableList<Tag>

    lateinit var mSearchText : EditText
    lateinit var mRecyclerView : RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag)

        // Display Toolbar
        var toolbar = findViewById(R.id.toolbar2) as Toolbar
        setSupportActionBar(toolbar)

        // Get Bitmap Data From AddActivity
        val bitmapSrc = intent.getParcelableExtra<Bitmap>("photoSrc")
        photoImageView?.setImageBitmap(bitmapSrc as Bitmap)


        // Retrieve data firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("tag")

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


        mDatabase!!.addValueEventListener(tagListener)



        supportActionBar!!.title = "Go Back"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // Search Live
        mSearchText = findViewById(R.id.searchtext)
        mRecyclerView = findViewById(R.id.list_view)
    }
}
