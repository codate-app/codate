package com.donxux.codate.presentation.view.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.donxux.codate.databinding.ViewChatReceiveMessageBinding
import com.donxux.codate.databinding.ViewChatSendMessageBinding
import com.donxux.codate.domain.model.ChatMessage
import java.lang.IllegalArgumentException
import kotlin.math.min

class ChatMessageListAdapter(diffCallback: DiffUtil.ItemCallback<ChatMessage>) :
    PagingDataAdapter<ChatMessage, RecyclerView.ViewHolder>(diffCallback) {

    companion object {

        private const val receiveMessageViewType = 0
        private const val sendMessageViewType = 1
    }

    override fun getItemViewType(position: Int): Int {
        val message = getItem(position)
        return if (message!!.isSender) {
            sendMessageViewType
        } else {
            receiveMessageViewType
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            if (item.isSender) {
                (holder as ChatSendMessageViewHolder).bind(item)
            } else {
                (holder as ChatReceiveMessageViewHolder).bind(item)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            receiveMessageViewType -> {
                val binding = ViewChatReceiveMessageBinding.inflate(inflater, parent, false)
                ChatReceiveMessageViewHolder(binding)
            }

            sendMessageViewType -> {
                val binding = ViewChatSendMessageBinding.inflate(inflater, parent, false)
                ChatSendMessageViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Undefined view type")
        }
    }
}

class ChatReceiveMessageViewHolder(private val binding: ViewChatReceiveMessageBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ChatMessage) {
        binding.chatReceiveText.text = item.message
        setCardWidth(cardView = binding.chatReceiveCard)
    }
}

class ChatSendMessageViewHolder(private val binding: ViewChatSendMessageBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ChatMessage) {
        binding.chatSendText.text = item.message
        setCardWidth(cardView = binding.chatSendCard)
    }
}

object ChatMessageComparator : DiffUtil.ItemCallback<ChatMessage>() {
    override fun areItemsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
        return oldItem == newItem
    }
}

private fun setCardWidth(cardView: CardView, maxWidthPercent: Double = DEFAULT_MAX_WIDTH_PERCENT) {
    cardView.post {
        val parentWidth = (cardView.parent as View).width
        val width =
            min(cardView.width, (parentWidth * maxWidthPercent).toInt())
        val params = cardView.layoutParams
        params.width = width
        cardView.layoutParams = params
    }
}

private const val DEFAULT_MAX_WIDTH_PERCENT = 0.7
