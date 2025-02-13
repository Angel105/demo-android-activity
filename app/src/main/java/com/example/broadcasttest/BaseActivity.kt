package com.example.broadcasttest

import android.content.Context
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {


    lateinit var receiver: ForceOfflineReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
    }


    override fun onResume() {
        super.onResume()
        Log.d("BaseActivity", "onResume - Registering receiver") // Added log
        val intentFilter  = IntentFilter()
        intentFilter.addAction("com.example.broadcasttest.FORCE_OFFLINE")
        receiver = ForceOfflineReceiver()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // API level 26 and higher
            registerReceiver(receiver, intentFilter, Context.RECEIVER_EXPORTED)
        } else {
            // Lower than API level 26
            registerReceiver(receiver, intentFilter)
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("BaseActivity", "onPause - Unregistering receiver") // Added log
        unregisterReceiver(receiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

}