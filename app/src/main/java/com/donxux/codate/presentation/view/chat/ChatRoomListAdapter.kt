package com.donxux.codate.presentation.view.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.donxux.codate.databinding.ViewChatRoomBinding
import com.donxux.codate.domain.model.ChatRoom

class ChatRoomListAdapter(
    diffCallback: DiffUtil.ItemCallback<ChatRoom>,
    private val itemClickListener: (Long) -> Unit
) :
    PagingDataAdapter<ChatRoom, ChatRoomViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: ChatRoomViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item) { itemClickListener(item.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRoomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewChatRoomBinding.inflate(inflater, parent, false)
        return ChatRoomViewHolder(binding)
    }
}

class ChatRoomViewHolder(private val binding: ViewChatRoomBinding) :
    ViewHolder(binding.root) {
    fun bind(item: ChatRoom, onClickListener: View.OnClickListener) {
        binding.chat = item
        binding.chatRoot.setOnClickListener(onClickListener)
        binding.chatProfileImage.load(item.image)
    }
}

object ChatRoomComparator : DiffUtil.ItemCallback<ChatRoom>() {
    override fun areItemsTheSame(oldItem: ChatRoom, newItem: ChatRoom): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChatRoom, newItem: ChatRoom): Boolean {
        return oldItem == newItem
    }
}
