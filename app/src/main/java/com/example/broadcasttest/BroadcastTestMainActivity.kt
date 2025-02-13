package com.example.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.databinding.ActivityBroadcastTestMainBinding

class BroadcastTestMainActivity : BaseActivity() {

    inner class TimeChangeReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "Time has changed", Toast.LENGTH_SHORT).show()
        }
    }

    lateinit var timeChangeReceiver: TimeChangeReceiver
    private lateinit var binding: ActivityBroadcastTestMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBroadcastTestMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter)

        binding.forceOffline.setOnClickListener {
            Log.d("BroadcastTestMainAct", "forceOffline - Button Clicked") // Added log
            val intent = Intent("com.example.broadcasttest.FORCE_OFFLINE")
//            intent.setPackage(packageName)
            sendBroadcast(intent)
            Log.d("BroadcastTestMainAct", "forceOffline - Broadcast Sent") // Log when broadcast is sent
        }
        supportActionBar?.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }
}