package com.example.broadcasttest

import android.content.Context
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

        val prefs = getPreferences(Context.MODE_PRIVATE)
        val isRemember =  prefs.getBoolean("remember_password", false)
        if (isRemember) {
            // Set the account and password info in the EditText views
            val account = prefs.getString("account", "")
            val password= prefs.getString("password", "")
            binding.accountEdit.setText(account)
            binding.passwordEdit.setText(password)
            binding.rememberPass.isChecked = true
        }

        binding.login.setOnClickListener {
            val account = binding.accountEdit.text.toString()
            val password= binding.passwordEdit.text.toString()

            // Correct account is student and password is student
            if (account=="student" && password=="student") {
                val editor = prefs.edit()
                if (binding.rememberPass.isChecked) {
                    editor.putBoolean("remember_password", true)
                    editor.putString("account", account)
                    editor.putString("password", password)
                } else {
                    editor.clear()
                }
                editor.apply()
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