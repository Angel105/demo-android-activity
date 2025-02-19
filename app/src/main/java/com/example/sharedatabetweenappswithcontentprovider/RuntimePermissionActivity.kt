package com.example.sharedatabetweenappswithcontentprovider

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.activitytest.databinding.ActivityRuntimePermissionBinding

class RuntimePermissionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRuntimePermissionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRuntimePermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.makeCall.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_CALL)
                intent.data = Uri.parse("tel:0123456789")
                startActivity(intent)
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
        }

    }
}