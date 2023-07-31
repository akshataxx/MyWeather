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
        if(fieldsNotEmpty()) {
            if (emailValidator()) {
                if(passwordValidator()){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this, "Password does not meet required length", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Email field does not have correct values", Toast.LENGTH_SHORT).show()
            }
        }
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

    /**
     * Method for email validation
     * Email field must have an appropriate email with @ and .com in the field
     */
    private fun emailValidator(): Boolean{
        return (android.util.Patterns.EMAIL_ADDRESS.matcher(emailValue.text.toString()).matches())
    }

    /**
     * Method for password length validation
     * Password entered must be greater than 6 characters and less than 12 characters
     */
    private fun passwordValidator(): Boolean{
        val minPasswordLength:Int = 6
        val maxPasswordLength:Int = 12
        if(passwordValue.length()< minPasswordLength || passwordValue.length() > maxPasswordLength)
        {
            return(false)
        }
        return(true)
    }
}