package com.flowapp.nzchos.flow

import android.content.Intent
import android.graphics.Bitmap
import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.*
import android.widget.LinearLayout.VERTICAL
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.flowapp.nzchos.flow.R.id.photoImageView

import com.flowapp.nzchos.flow.R.styleable.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat
import ir.mirrajabi.searchdialog.core.SearchResultListener
import kotlinx.android.synthetic.main.activity_tag.*
import kotlinx.android.synthetic.main.fragment_tab_best.*
import org.jetbrains.anko.toolbar

class TagActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference
    lateinit var tagList : MutableList<Tag>
    lateinit var selectedTagList : ListView
    val tagArray: ArrayList<String> = ArrayList()
    val tagSelected: ArrayList<String> = ArrayList()

    //lateinit var mSearchText : EditText
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

                    println(message)
                    for (snapshot in tagSnapshot.children) {
                        val snapContent: ArrayList<String> = ArrayList()
                        val name = snapshot.value as HashMap<String, Any>

                        for ((key, value) in name) {
                            println("snape"+key)
                            tagArray.add(key)
                        }


                    }

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Failed to read value
            }
        }


        mDatabase!!.addValueEventListener(tagListener)
        supportActionBar!!.title = "Go Back"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        // Simple Search
        btnSearch.setOnClickListener{
            val tagDismiss = findViewById<View>(R.id.tagView)
            tagDismiss.visibility = View.VISIBLE

            /*SimpleSearchDialogCompat(this@TagActivity, "Search", "Add Tag", null, initData(), SearchResultListener { baseSearchDialogCompat, item, position ->
                Toast.makeText(this@TagActivity, item.title, Toast.LENGTH_SHORT).show()
                println("TITLE"+item.title)
                tagSelected.add(item.title)
                println("selected tag: " + tagSelected)



                //baseSearchDialogCompat.dismiss()


            }).show()*/
            // Validation Tag Button
            buttonValidTag.setOnClickListener {
                println(tagSelected)
                tagDismiss.visibility = View.GONE
            }

            // Update selected tag list
           /* val selectedTagList = findViewById<ListView>(R.id.selectedTags)
            val recipeList = Recipe.getRecipesFromFile("recipes.json", this)
// 2
            val listItems = arrayOfNulls<String>(recipeList.size)
// 3
            for (i in 0 until recipeList.size) {
                val recipe = recipeList[i]
                listItems[i] = recipe.title
            }
// 4
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
            selectedTagList.adapter = adapter*/

            // VALIDATION OUTFIT BUTTON
            outfitRegister.setOnClickListener {
                registerOutfit()
            }

        }
        tagRecycler.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        tagRecycler.adapter = tagOutfitAdapter(tagArray,tagSelected, this)


    }

    private fun registerOutfit() {
        val emailTxt = findViewById<View>(R.id.register_email) as EditText
        val passwordTxt = findViewById<View>(R.id.register_password) as EditText
        val nameTxt = findViewById<View>(R.id.nameTxt) as EditText

        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()
        var name = nameTxt.text.toString()

        if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    val uid = user!!.uid
                    mDatabase.child(uid).child("username").setValue(name)
                    mDatabase.child(uid).child("email").setValue(email)
                    startActivity(Intent(this, HomeActivity::class.java))
                    Toast.makeText(this, "Successfully registered :)", Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(this, "Error registering, try again later :(", Toast.LENGTH_LONG).show()
                }
            })
        }else {
            Toast.makeText(this,"Please fill up the Credentials :|", Toast.LENGTH_LONG).show()
        }
    }




    private fun initData():ArrayList<SearchModel> {

        val items = ArrayList<SearchModel>()
        for (i in 0..tagArray.size-1) {
            items.add(SearchModel(tagArray[i]))
        }

        return items
    }
}
