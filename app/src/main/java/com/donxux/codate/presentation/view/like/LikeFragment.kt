package com.donxux.codate.presentation.view.like

import android.content.res.Configuration
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

    companion object {
        const val maxRowOnPortrait = 2
        const val maxRowOnLandscape = 4
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLikeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[LikeViewModel::class.java]
        binding.viewModel = viewModel
        binding.likeFlow.setMaxElementsWrap(
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                maxRowOnLandscape
            } else {
                maxRowOnPortrait
            }
        )
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
