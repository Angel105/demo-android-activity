package com.example.activitytest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.activitytest.databinding.ActivityFragmentBinding

class FragmentActivity : AppCompatActivity(), FragmentNavigation {

    private lateinit var binding: ActivityFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate the main layout first
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(RightFragment())
        supportActionBar?.hide()
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.rightLayout, fragment)
        transaction.commit()
    }

    override fun navigateToFragment(fragment: Fragment) {
        replaceFragment(fragment)
    }
}