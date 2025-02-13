package com.example.broadcasttest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            val account = binding.accountEdit.text.toString()
            val password= binding.passwordEdit.text.toString()

            // Correct account is student and password is student
            if (account=="student" && password=="student") {
                val intent = Intent(this, BroadcastTestMainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "account or password is invalid", Toast.LENGTH_SHORT).show()
            }

        }
        supportActionBar?.hide()
    }
}