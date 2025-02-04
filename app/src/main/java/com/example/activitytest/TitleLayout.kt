package com.example.activitytest

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.example.activitytest.databinding.TitleLayoutBinding

class TitleLayout(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    private val binding: TitleLayoutBinding

    init {
        binding = TitleLayoutBinding.inflate(LayoutInflater.from(context), this, true)
        binding.titleBack.setOnClickListener {
            val activity = context as Activity
            activity.finish()
        }
        binding.titleEdit.setOnClickListener {
            Toast.makeText(context, "You clicked Edit button", Toast.LENGTH_SHORT).show()
        }
    }
}