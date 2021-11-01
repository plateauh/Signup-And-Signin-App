package com.najed.signupandsigninapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val editTexts = listOf<EditText>(
            findViewById(R.id.name_et),
            findViewById(R.id.mobile_et),
            findViewById(R.id.location_et),
            findViewById(R.id.password_et)
        )

        val signUpButton = findViewById<Button>(R.id.sign_up_btn)
        signUpButton.setOnClickListener {
            val isStored = DBHelper(applicationContext).storeUser(
                User(
                editTexts[0].text.toString(),
                editTexts[1].text.toString(),
                editTexts[2].text.toString() ),
                editTexts[3].text.toString()
            )
            if (isStored){
                Toast.makeText(this, "Signed up successfully!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("caller", "SignUp")
                intent.putExtra("name", editTexts[0].text.toString())
                intent.putExtra("mobile", editTexts[1].text.toString())
                intent.putExtra("location", editTexts[2].text.toString())
                startActivity(intent)
            }
            else
                Toast.makeText(this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show()
        }
    }
}