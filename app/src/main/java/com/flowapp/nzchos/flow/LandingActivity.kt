package com.flowapp.nzchos.flow

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LandingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        val buttonConnexion = findViewById<Button>(R.id.open_connexion)
        buttonConnexion.setOnClickListener{
        val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val buttonInscription = findViewById<Button>(R.id.open_inscription)
        buttonInscription.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
