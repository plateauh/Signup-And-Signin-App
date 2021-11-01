package com.najed.signupandsigninapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val signinButton = findViewById<Button>(R.id.Signin_btn)
        signinButton.setOnClickListener {
            startActivity(Intent(this, SigninActivity::class.java))
            finish()
        }

        val signupButton = findViewById<Button>(R.id.Signup_btn)
        signupButton.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }
    }
}