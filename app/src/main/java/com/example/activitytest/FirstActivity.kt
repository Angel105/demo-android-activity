package com.example.activitytest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.activitytest.databinding.FirstLayoutBinding

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: FirstLayoutBinding
    private lateinit var startForResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FirstActivity", this.toString())
        binding = FirstLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Register the contract using the modern registerForActivityResult API
        startForResult = registerForActivityResult(ActivityResultContracts
            .StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val returnedData = data?.getStringExtra("data_return")
                Toast.makeText(this, returnedData, Toast.LENGTH_SHORT).show()
                Log.d("FirstActivity", "returned data is $returnedData")
            }

        }
        binding.button1.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
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