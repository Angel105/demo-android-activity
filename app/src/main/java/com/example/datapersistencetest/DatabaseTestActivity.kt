package com.example.datapersistencetest

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

        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 1)
        binding.createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }
        supportActionBar?.hide()
    }
}