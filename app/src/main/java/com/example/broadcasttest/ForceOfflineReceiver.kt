package com.example.broadcasttest

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ForceOfflineReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("ForceOfflineReceiver", "onReceive: - Broadcast Received") // Added
        try {
            // Start the ForceOfflineActivity
            val i = Intent(context, ForceOfflineActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                i,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
            pendingIntent.send()

        } catch (e: Exception) {
            Log.e("ForceOfflineReceiver", "Error in onReceive", e)
             // Handle the exception gracefully, if possible
        }

    }

}