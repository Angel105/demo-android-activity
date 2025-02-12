package com.example.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BootCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Toast.makeText(context, "Boot Complete", Toast.LENGTH_SHORT).show()
        // It is worth noting that we don’t add too many operations or time
        //consuming operations in onReceive() because BroadcastReceiver cannot start a new
        //thread, and if onReceive() has been running for too long, exceptions will be thrown.
    }
}