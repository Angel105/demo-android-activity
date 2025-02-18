package com.example.datapersistencetest

import android.content.ContentValues
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.activitytest.databinding.ActivityDatabaseTestBinding

class DatabaseTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDatabaseTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDatabaseTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        binding.createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }

        binding.addData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                // construct the first row of data
                put("name", "Robinson Crusoe")
                put("author", "Daniel Defoe")
                put("pages", 144)
                put("price", 9.65)
            }
            // insert the first row of data
            db.insert("Book", null, values1)
            val values2 = ContentValues().apply {
                // construct the second row of data
                put("name", "De Drie Musketiers")
                put("author", "Alexandre Dumas")
                put("pages", 355)
                put("price", 4.9)
            }
            // insert the second row of data
            db.insert("Book", null, values2)
        }

        binding.updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 5.45)
            db.update("Book", values, "name = ?", arrayOf("Robinson Crusoe"))
        }

        supportActionBar?.hide()
    }
}