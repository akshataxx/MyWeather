package com.example.myweather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myweather.R.*


/**
 * Loginn activity for android studio
 */
private const val TAG = "MainActivity"

class LoginActivity : AppCompatActivity() {

    private lateinit var emailValue: TextView
    private lateinit var passwordValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_login)
        emailValue = findViewById(id.emailLoginValue)
        passwordValue = findViewById(id.passwordLoginValue)

        val loginButton = findViewById<Button>(id.loginButton)
        loginButton.setOnClickListener { login() } //set click listener
    }

    /**
     * function triggered when all fields are filled and login button is clicked
     * redirect to weather page
     * validates all fields have values
     * TODO: values are correct
     */

    private fun login() {
        if(fieldsNotEmpty()){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)}
        else{
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * method to check whether fields are populated
     */
    private fun fieldsNotEmpty(): Boolean {
        return  emailValue.text.toString().isNotEmpty() &&
                passwordValue.text.toString().isNotEmpty()
    }
}