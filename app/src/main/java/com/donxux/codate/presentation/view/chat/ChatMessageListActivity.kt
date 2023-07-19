package com.donxux.codate.presentation.view.chat

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.donxux.codate.R
import com.donxux.codate.databinding.ActivityChatMessageListBinding
import com.donxux.codate.presentation.viewmodel.ChatMessageListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatMessageListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatMessageListBinding

    private var chatId: Long? = null

    private val chatMessageListViewModel by viewModels<ChatMessageListViewModel>()

    private var chatMessageAdapter: ChatMessageListAdapter? = null

    companion object {
        const val idKey = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay_in_place)
        setOnBackPressed()
        super.onCreate(savedInstanceState)
        val id = intent?.getLongExtra(idKey, -1L)
        assert(id != -1L) {
            "ChatRoomFragment must have a Chat ID. " + "Pass the ID using the newInstance() method."
        }
        chatId = id

        setChatMessageAdapter()
        binding = ActivityChatMessageListBinding.inflate(layoutInflater)
        binding.chatMessages.layoutManager = LinearLayoutManager(this)
        binding.chatMessages.adapter = chatMessageAdapter
        setDisplayBeforeNavigationIcon()
        collectChatMessages()
        setContentView(binding.root)
    }

    private fun collectChatMessages() {
        lifecycleScope.launch(Dispatchers.IO) {
            chatMessageListViewModel.getChatMessagesPagingData()
                .collectLatest(chatMessageAdapter!!::submitData)
        }
    }

    private fun setChatMessageAdapter() {
        chatMessageAdapter = ChatMessageListAdapter(ChatMessageComparator)
    }

    private fun setOnBackPressed() {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    finishWithAnimation()
                }
            }
        )
    }

    private fun setDisplayBeforeNavigationIcon() {
        setSupportActionBar(binding.chatToolbar)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finishWithAnimation()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun finishWithAnimation() {
        finish()
        overridePendingTransition(R.anim.stay_in_place, R.anim.slide_out_right)
    }
}
