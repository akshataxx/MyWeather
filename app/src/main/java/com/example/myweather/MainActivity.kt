package com.example.myweather

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.ComponentActivity

/*import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview*/
import com.example.myweather.ui.theme.MyWeatherTheme
import androidx.appcompat.app.AppCompatActivity

/**
 * Main Activity class extends App combat activity
 * onCreate method is overridden to set the content view of the activity using setContentView

 */

private const val TAG ="MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var city:AutoCompleteTextView
    private lateinit var suggestion: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //set the xml layout for this activity
        city = findViewById(R.id.SearchBar)
        suggestion = findViewById(R.id.dropdownSuggestion)

        city.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after:Int){}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "afterTextChanged $s")
                //submit button to trigger openweather api call
                submitButton()

            }
        })
    }

    /**
     * Function to fetch weather conditions for the selected city
     */
    private fun fetchWeather(city:String){
        val weatherConditions = "Weather conditions for $city: Sunny "

    }

    /**
     * Function to be triggered when submit button is clicked
     */
    private fun submitButton(view: View){
        val selectedCity = city.text.toString()
        fetchWeather(selectedCity)
    }
}







