package com.example.activitytest

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.activitytest.databinding.ActivityListViewBinding

class ListViewActivity : AppCompatActivity() {

    private val data = listOf(
        "Apple", "Banana", "Orange", "Watermelon",
        "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
        "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape",
        "Pineapple", "Strawberry", "Cherry", "Mango"
    )

    private lateinit var binding: ActivityListViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListViewBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
        binding.listView.adapter = adapter
    }

}