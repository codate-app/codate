package com.donxux.codate.presentation.view.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.donxux.codate.databinding.FragmentChatBinding
import com.donxux.codate.presentation.viewmodel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null

    private val binding get() = _binding!!

    private val chatViewModel by viewModels<ChatViewModel>()

    private var chatAdapter: ChatAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatAdapter = ChatAdapter(ChatComparator)
        binding.chatChatList.layoutManager = LinearLayoutManager(requireContext())
        binding.chatChatList.adapter = chatAdapter
        collectChats()
        collectChatAdapter()
    }

    private fun collectChats() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            chatViewModel.getChatPagingData().collectLatest(chatAdapter!!::submitData)
        }
    }

    private fun collectChatAdapter() = viewLifecycleOwner.lifecycleScope.launch {
        chatAdapter?.loadStateFlow?.collectLatest { loadStates ->
            binding.chatProgress.visibility =
                if (loadStates.refresh is LoadState.Loading) View.VISIBLE else View.INVISIBLE
            if (loadStates.refresh is LoadState.NotLoading) {
                binding.chatProgress.visibility =
                    View.INVISIBLE
            }
            if (loadStates.refresh is LoadState.Error) {
                Log.d("ChatFragment TEST", loadStates.refresh.toString())
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        chatAdapter = null
        super.onDestroyView()
    }
}
