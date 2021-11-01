package com.najed.signupandsigninapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        val mobileEditText = findViewById<EditText>(R.id.login_mobile_et)
        val passwordEditText = findViewById<EditText>(R.id.login_password_et)
        val signInButton = findViewById<Button>(R.id.sign_in_btn)
        signInButton.setOnClickListener {
            val isUser = DBHelper(applicationContext).authenticateUser(
                mobileEditText.text.toString(), passwordEditText.text.toString())
            if (isUser) {
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("mobile", mobileEditText.text.toString())
                startActivity(intent)
                Toast.makeText(this, "Signed in successfully", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Wrong combination, make sure you entered a valid mobile/password",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}