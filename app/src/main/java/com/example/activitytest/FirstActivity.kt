package com.example.activitytest

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.activitytest.databinding.FirstLayoutBinding

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: FirstLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FirstLayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.button1.setOnClickListener {
            val intent = Intent("com.example.activitytest.ACTION_START")
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(optionsMenu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, optionsMenu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}