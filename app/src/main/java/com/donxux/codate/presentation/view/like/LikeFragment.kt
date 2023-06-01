package com.donxux.codate.presentation.view.like

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.donxux.codate.databinding.FragmentLikeBinding
import com.donxux.codate.presentation.viewmodel.LikeViewModel

class LikeFragment : Fragment() {
    private var _binding: FragmentLikeBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: LikeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLikeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[LikeViewModel::class.java]
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
