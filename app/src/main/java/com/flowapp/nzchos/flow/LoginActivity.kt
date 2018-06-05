package com.flowapp.nzchos.flow

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import java.util.ArrayList
import android.Manifest.permission.READ_CONTACTS
import android.content.Intent
import android.os.PersistableBundle
import android.widget.*

import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginBtn = findViewById<View>(R.id.email_sign_in_button) as Button
        /**val registerTxt = findViewById<View>(R.id.regTxt) as TextView**/

        loginBtn.setOnClickListener(View.OnClickListener {
            view -> login()
        })

        /**registerTxt.setOnClickListener(View.OnClickListener {
            view -> register()
        })**/

    }

    // LOGIN FUNCTION
    private fun login () {
        val emailTxt = findViewById<View>(R.id.email) as EditText
        var email = emailTxt.text.toString()
        val passwordTxt = findViewById<View>(R.id.password) as EditText
        var password = passwordTxt.text.toString()

        if (!email.isEmpty() && !password.isEmpty()) {
            this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener ( this, OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, HomeActivity::class.java).apply { putExtra("email", email) })
                    Toast.makeText(this, "Successfully Logged in :)", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Error Logging in :(", Toast.LENGTH_SHORT).show()
                }
            })

        }else {
            Toast.makeText(this, "Please fill up the Credentials :|", Toast.LENGTH_SHORT).show()
        }
    }

    // REGISTER
    private fun register () {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}
