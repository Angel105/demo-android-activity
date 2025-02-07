package com.example.activitytest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(data)

        binding.recyclerView.adapter = adapter
    }

    private fun initFruits() {
        repeat(2) {
            data.add(Fruit(getRandomLengthString("Apple"), R.drawable.apple_pic))
            data.add(Fruit(getRandomLengthString("Banana"), R.drawable.banana_pic))
            data.add(Fruit(getRandomLengthString("Orange"), R.drawable.orange_pic))
            data.add(Fruit(getRandomLengthString("Watermelon"), R.drawable.watermelon_pic))
            data.add(Fruit(getRandomLengthString("Pear"), R.drawable.pear_pic))
            data.add(Fruit(getRandomLengthString("Grape"), R.drawable.grape_pic))
            data.add(Fruit(getRandomLengthString("Pineapple"), R.drawable.pineapple_pic))
            data.add(Fruit(getRandomLengthString("Strawberry"), R.drawable.strawberry_pic))
            data.add(Fruit(getRandomLengthString("Cherry"), R.drawable.cherry_pic))
            data.add(Fruit(getRandomLengthString("Mango"), R.drawable.mango_pic))
        }
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }

}