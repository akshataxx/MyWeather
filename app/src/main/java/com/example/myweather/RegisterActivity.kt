package com.example.myweather

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.myweather.LoginActivity

/**
 * Register activity for android studio
 */

private const val TAG = "RegisterActivity"
private const val LOG = "LoginActivity"


class RegisterActivity : AppCompatActivity() {

    private lateinit var firstNameValue: TextView
    private lateinit var lastNameValue: TextView
    private lateinit var addressValue: TextView
    private lateinit var emailValue: TextView
    private lateinit var password : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        firstNameValue = findViewById(R.id.firstNameValue)
        lastNameValue = findViewById(R.id.lastNameValue)
        addressValue = findViewById(R.id.addressValue)
        emailValue = findViewById(R.id.emailValue)
        password = findViewById(R.id.passwordValue)

        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener { register() } //set click listener
    }

    /**
     * function triggered when all fields are filled and register button is clicked
     * redirect to login page
     * validates all fields have values
     */

    private fun register() {
        if(fieldsNotEmpty()){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)}
        else{
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show()
        }
    }


    private fun fieldsNotEmpty(): Boolean{
        return firstNameValue.text.toString().isNotEmpty() &&
                lastNameValue.text.toString().isNotEmpty() &&
                addressValue.text.toString().isNotEmpty() &&
                emailValue.text.toString().isNotEmpty() &&
                password.text.toString().isNotEmpty()

    }
}
