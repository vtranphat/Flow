package com.flowapp.nzchos.flow

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_tab_new.*


class TabNewFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_tab_new, container, false)


    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val posts: ArrayList<String> = ArrayList()

        for (i in 1..100) {
            posts.add("Post # $i")
        }

        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext, OrientationHelper.HORIZONTAL, false)
        recyclerView.adapter = PostsAdapter(posts)
    }
}