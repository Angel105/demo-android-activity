package com.example.activitytest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.activitytest.databinding.NewsContentFragBinding

class NewsContentFragment : Fragment() {

//    private lateinit var binding: NewsContentFragBinding
    private var _binding: NewsContentFragBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        binding = NewsContentFragBinding.inflate(layoutInflater)
        _binding = NewsContentFragBinding.inflate(inflater, container, false)

        return binding.root // inflater.inflate(R.layout.news_content_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Now you can safely use 'binding' here
        // Retrieve data from arguments
        val title = arguments?.getString("news_title")
        val content = arguments?.getString("news_content")

        // Now it's safe to call refresh() because the view is created
        if (title != null && content != null) {
            refresh(title, content)
        }

    }

    fun refresh(title: String, content: String) {
        binding.contentLayout.visibility = View.VISIBLE
        binding.newsTitle.text = title
        binding.newsContent.text = content
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}