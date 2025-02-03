package com.example.activitytest

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activitytest.databinding.NormalLayoutBinding

class NormalActivity : AppCompatActivity() {

    private lateinit var binding: NormalLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = NormalLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.button.setOnClickListener {
            // Add event handling logic here
            Log.d("NormalActivity", "Button clicked")
            var inputText = binding.editText.text.toString()
            if (inputText.isEmpty()) {
                inputText = "Please enter some text"
            }
            Toast.makeText(this, inputText, Toast.LENGTH_SHORT).show()
            binding.imageView.setImageResource(R.drawable.img_2)
            if (binding.progressBar.visibility == View.VISIBLE) {
                binding.progressBar.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.VISIBLE
            }
        }

    }
}