package com.donxux.codate.presentation.view.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.donxux.codate.databinding.FragmentExploreBinding
import com.donxux.codate.presentation.viewmodel.ExploreViewModel

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ExploreViewModel::class.java]
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
