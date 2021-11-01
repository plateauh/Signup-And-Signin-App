package com.najed.signupandsigninapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val textViews = listOf<TextView>(
            findViewById(R.id.welcome_tv),
            findViewById(R.id.location_tv),
            findViewById(R.id.name_tv)
        )
        if (intent.getStringExtra("caller").equals("SignUp")){
            textViews[0].text = "Welcome ${intent.getStringExtra("mobile")},"
            textViews[1].text = "Location: ${intent.getStringExtra("location")}"
            textViews[2].text = "Name: ${intent.getStringExtra("name")}"
        }
        else {
            val mobile = intent.getStringExtra("mobile")
            val user = mobile?.let { DBHelper(applicationContext).getUser(it) }
            textViews[0].text = "Welcome ${user?.mobile}"
            textViews[1].text = "Location: ${user?.location}"
            textViews[2].text = "Name: ${user?.name}"
        }

        val signoutButton = findViewById<Button>(R.id.signout_btn)
        signoutButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(this, "See you later alligator 🐊", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}