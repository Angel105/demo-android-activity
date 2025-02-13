package com.example.broadcasttest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ForceOfflineActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AlertDialog.Builder(this).apply {
            setTitle("Warning")
            setMessage("You are forced to be offline. Please try to login again.")
            setCancelable(false)
            setPositiveButton("OK") {_, _ ->
                ActivityCollector.finishAll()

                val i = Intent(this@ForceOfflineActivity, LoginActivity::class.java)
                startActivity(i)

            }
            show()
        }
    }
}