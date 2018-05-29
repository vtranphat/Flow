package com.flowapp.nzchos.flow

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_home.*
import com.flowapp.nzchos.flow.R

import com.flowapp.nzchos.flow.R.layout.fragment_home
import android.support.v4.app.FragmentActivity
import android.app.Activity


class fragmentHome : Fragment(), FirstFragment.FirstFragmentListener {
    override fun onclick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

        val firstFragment = FirstFragment()
        firstFragment.listener = this

        childFragmentManager.beginTransaction()
                .add(R.id.tabFragmentContainer, firstFragment)
                .commit()
    }
}
