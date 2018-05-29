package com.flowapp.nzchos.flow

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.flowapp.nzchos.flow.R

import com.flowapp.nzchos.flow.R.id.fragment_container
import com.flowapp.nzchos.flow.R.menu.bottom_navigation

class HomeActivity : AppCompatActivity() {

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null

        when (item.itemId) {
            R.id.navigation_home -> selectedFragment = fragmentHome()
            R.id.navigation_add -> selectedFragment = fragmentAdd()
            R.id.navigation_user -> selectedFragment = fragmentUser()
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit()

        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNav = findViewById(R.id.bottom_navigation) as BottomNavigationView
        bottomNav.setOnNavigationItemSelectedListener(navListener)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                    fragmentHome()).commit()
        }
    }
}