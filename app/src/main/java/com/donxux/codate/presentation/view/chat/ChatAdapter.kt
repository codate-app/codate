package com.donxux.codate.presentation.view.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.donxux.codate.databinding.ViewChatBinding
import com.donxux.codate.domain.model.Chat

class ChatAdapter(diffCallback: DiffUtil.ItemCallback<Chat>) :
    PagingDataAdapter<Chat, ChatViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewChatBinding.inflate(inflater, parent, false)
        return ChatViewHolder(binding)
    }
}

class ChatViewHolder(private val binding: ViewChatBinding) : ViewHolder(binding.root) {
    fun bind(item: Chat) {
        binding.chat = item
        binding.chatProfileImage.load(item.image)
    }
}

object ChatComparator : DiffUtil.ItemCallback<Chat>() {
    override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem == newItem
    }
}
