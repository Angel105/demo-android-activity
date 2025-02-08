package com.example.activitytest

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitytest.databinding.ActivityUiBestPracticeBinding

class UIBestPracticeActivity : AppCompatActivity(), View.OnClickListener {

    private val msgList = ArrayList<Msg>()

    private var adapter: MsgAdapter? = null

    private lateinit var binding: ActivityUiBestPracticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ui_best_practice)
        binding = ActivityUiBestPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initMsg()

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        adapter = MsgAdapter(msgList)
        binding.recyclerView.adapter = adapter
        binding.send.setOnClickListener(this)

    }

    private fun initMsg() {
        val msg1 = Msg("Hello, folks!", Msg.TYPE_RECEIVED)
        val msg2 = Msg("Hello! Who are you?", Msg.TYPE_SENT)
        val msg3 = Msg("It's me, Rob!, Nice talking to you again, folks!", Msg.TYPE_RECEIVED)
        msgList.addAll(listOf(msg1, msg2, msg3))
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.send -> {
                val content = binding.inputText.text.toString()
                Log.d("UIBestPracticeActivity", "onClick, text $content")
                if (content.isNotEmpty()) {
                    val msg = Msg(content, Msg.TYPE_SENT)
                    msgList.add(msg)
                    adapter?.notifyItemInserted(msgList.size - 1) // when there is a new msg, refresh RecyclerView
                    binding.recyclerView.scrollToPosition(msgList.size - 1) // scroll RecyclerView to the last item
                    binding.inputText.text.clear() // clear the EditText view
                }
            }
        }
    }
}
