package com.donxux.codate.presentation.view.chat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.donxux.codate.databinding.FragmentChatRoomListBinding
import com.donxux.codate.presentation.viewmodel.ChatRoomListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatRoomListFragment : Fragment() {
    private var _binding: FragmentChatRoomListBinding? = null

    private val binding get() = _binding!!

    private val chatRoomViewModel by viewModels<ChatRoomListViewModel>()

    private var chatRoomListAdapter: ChatRoomListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatRoomListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatRoomListAdapter = ChatRoomListAdapter(ChatRoomComparator) { id ->
            val intent = Intent(requireActivity(), ChatMessageListActivity::class.java).apply {
                putExtra(ChatMessageListActivity.idKey, id)
            }
            startActivity(intent)
        }
        binding.chatChatList.layoutManager = LinearLayoutManager(requireContext())
        binding.chatChatList.adapter = chatRoomListAdapter
        collectChatRooms()
        collectChatAdapter()
    }

    private fun collectChatRooms() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            chatRoomViewModel.getChatRoomsPagingData().collectLatest(chatRoomListAdapter!!::submitData)
        }
    }

    private fun collectChatAdapter() = viewLifecycleOwner.lifecycleScope.launch {
        chatRoomListAdapter?.loadStateFlow?.collectLatest { loadStates ->
            binding.chatProgress.visibility =
                if (loadStates.refresh is LoadState.Loading) View.VISIBLE else View.INVISIBLE
            if (loadStates.refresh is LoadState.NotLoading) {
                binding.chatProgress.visibility =
                    View.INVISIBLE
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        chatRoomListAdapter = null
        super.onDestroyView()
    }
}
