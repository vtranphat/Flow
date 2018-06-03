package com.flowapp.nzchos.flow

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flowapp.nzchos.flow.R
import kotlinx.android.synthetic.main.fragment_user.*


class fragmentUser : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)


        /**
        user_recycler.layoutManager = LinearLayoutManager(this)
        user_recycler.adapter = PostsAdapter(posts)
        **/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val posts: ArrayList<String> = ArrayList()

        for (i in 1..100) {
            posts.add("Post # $i")
        }

        user_recycler.layoutManager = LinearLayoutManager(activity?.applicationContext, OrientationHelper.HORIZONTAL, false)
        user_recycler.adapter = UserOutfitsAdapter(posts)

    }


}