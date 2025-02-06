package com.example.activitytest

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitytest.databinding.ActivityListViewBinding

class ListViewActivity : AppCompatActivity() {

    private val data = ArrayList<Fruit>()

    private lateinit var binding: ActivityListViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListViewBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initFruits()  // initialize fruit data
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(data)

        binding.recyclerView.adapter = adapter
    }

    private fun initFruits() {
        repeat(2) {
            data.add(Fruit("Apple", R.drawable.apple_pic))
            data.add(Fruit("Banana", R.drawable.banana_pic))
            data.add(Fruit("Orange", R.drawable.orange_pic))
            data.add(Fruit("Watermelon", R.drawable.watermelon_pic))
            data.add(Fruit("Pear", R.drawable.pear_pic))
            data.add(Fruit("Grape", R.drawable.grape_pic))
            data.add(Fruit("Pineapple", R.drawable.pineapple_pic))
            data.add(Fruit("Strawberry", R.drawable.strawberry_pic))
            data.add(Fruit("Cherry", R.drawable.cherry_pic))
            data.add(Fruit("Mango", R.drawable.mango_pic))
        }
    }

}