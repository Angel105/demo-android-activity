package com.example.datapersistencetest

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.activitytest.databinding.ActivitySharedPreferencesTestBinding

class SharedPreferencesTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySharedPreferencesTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySharedPreferencesTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            // Save data
            Log.d("SharedPreferencesTest", "Saving data...")
            val sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
            sharedPreferences.edit {
                putString("name", "Andrew")
                putInt("age", 28)
                putBoolean("married", false)
            }
            /*val editor = sharedPreferences.edit()
            editor.putString("name", "Andrew")
            editor.putInt("age", 28)
            editor.putBoolean("married", false)
            editor.apply()*/

            restoreData(sharedPreferences)
        }

        binding.restoreButton.setOnClickListener {
            Log.d("SharedPreferencesTest", "Restoring data...")
            val sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
            restoreData(sharedPreferences)
        }

    }

    private fun restoreData(sharedPreferences: SharedPreferences) {
        // Retrieve data saved data
        val myString = sharedPreferences.getString("name", "Default Value")
        val myInt = sharedPreferences.getInt("age", 0)
        val myBoolean = sharedPreferences.getBoolean("married", false)

        // Display data
        Toast.makeText(this, "Name: $myString, Age: $myInt, Married: $myBoolean", Toast.LENGTH_LONG)
            .show()
        Log.d("SharedPreferencesTest", "Name: $myString, Age: $myInt, Married: $myBoolean")
    }

}