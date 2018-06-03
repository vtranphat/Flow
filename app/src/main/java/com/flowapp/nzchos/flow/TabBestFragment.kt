package com.flowapp.nzchos.flow

import android.bluetooth.le.AdvertisingSetCallback
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout.VERTICAL
import kotlinx.android.synthetic.main.fragment_tab_best.*


class TabBestFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab_best, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val posts: ArrayList<String> = ArrayList()

        for (i in 1..100) {
            posts.add("Post # $i")
        }

        newOutfitsRecycler.layoutManager = GridLayoutManager(activity?.applicationContext, 2, VERTICAL,  false)
        newOutfitsRecycler.adapter = NewOutfitsAdapter(posts)
    }

}// Required empty public constructor