package com.example.activitytest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.databinding.SecondActivityBinding

class SecondActivity : BaseActivity() {

    private lateinit var binding: SecondActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SecondActivity", "Task id is $taskId")
        enableEdgeToEdge()
        binding = SecondActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        // Handle the Back button press
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Return data when the Back button is presses
                val intent = Intent()
                intent.putExtra("data_return", "Hello FirstActivity from Back Button")
                setResult(RESULT_OK, intent)
                finish()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondActivity", "onDestroy")
    }

    companion object {
        fun actionStart(context: Context, data1: String, data2: String) {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("param1", data1)
            intent.putExtra("param2", data2)
            context.startActivity(intent)
        }
    }
}