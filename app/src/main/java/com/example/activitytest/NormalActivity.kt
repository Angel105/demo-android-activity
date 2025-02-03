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
            binding.apply {
                // Add event handling logic here
                // Access views directly without "binding."
                Log.d("NormalActivity", "Button clicked")
                var inputText = editText.text.toString()
                if (inputText.isEmpty()) {
                    inputText = "Please enter some text"
                }
                Toast.makeText(this@NormalActivity, inputText, Toast.LENGTH_SHORT).show()
                imageView.setImageResource(R.drawable.img_2)
                if (progressBar.visibility == View.VISIBLE) {
                    progressBar.visibility = View.GONE
                } else {
                    progressBar.visibility = View.VISIBLE
                }
            }
        }

    }
}