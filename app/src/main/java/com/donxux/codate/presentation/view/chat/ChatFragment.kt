package com.donxux.codate.presentation.view.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.donxux.codate.databinding.FragmentChatBinding
import com.donxux.codate.presentation.viewmodel.ChatViewModel

class ChatFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: ChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ChatViewModel::class.java]
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
