package com.example.activitytest

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.activitytest.databinding.LeftFragmentBinding

class LeftFragment: Fragment() {

    private lateinit var binding: LeftFragmentBinding
    private var fragmentNavigation: FragmentNavigation? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LeftFragmentBinding.inflate(layoutInflater)
        binding.button.setOnClickListener {
            Log.d("FragmentActivity", "replaceFragment")
            fragmentNavigation?.navigateToFragment(AnotherRightFragment())
        }
        return  binding.root //inflater.inflate(R.layout.left_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentNavigation) {
            fragmentNavigation = context
        } else {
            throw RuntimeException("$context must implement FragmentNavigation")
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentNavigation = null
    }

}