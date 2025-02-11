package com.example.activitytest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.activitytest.databinding.ActivityNewsContentBinding

class NewsContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsContentBinding

    companion object {
        fun actionStart(context: Context, title: String, content: String) {
            val intent = Intent(context, NewsContentActivity::class.java).apply {
                putExtra("news_title", title)
                putExtra("news_content", content)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNewsContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("news_title") // get the news title
        val content = intent.getStringExtra("news_content")// get the news content

        if (title != null && content != null) {
//            val fragment = binding.newsContentFrag as NewsContentFragment

            // Create a bundle to pass data to the fragment
            val bundle = Bundle().apply {
                putString("news_title", title)
                putString("news_content", content)
            }

            // Find the fragment
            val fragment = supportFragmentManager.findFragmentById(R.id.newsContentFrag) as? NewsContentFragment
//            fragment?.refresh(title, content) // refresh NewsContentFragment

            // Set the arguments to the fragment
            fragment?.arguments = bundle

        }

    }
}