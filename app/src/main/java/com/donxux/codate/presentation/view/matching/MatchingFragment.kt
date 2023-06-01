package com.donxux.codate.presentation.view.matching

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.donxux.codate.databinding.FragmentMatchingBinding
import com.donxux.codate.presentation.viewmodel.MatchingViewModel
import kotlinx.coroutines.launch

class MatchingFragment : Fragment() {

    private var _binding: FragmentMatchingBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: MatchingViewModel

    private lateinit var partnerCardsAdapter: PartnerCardsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchingBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MatchingViewModel::class.java]

        val partners = viewModel.partners.value
        partnerCardsAdapter =
            PartnerCardsAdapter(partners.toMutableList())
        binding.searchPartnerCardsPager.adapter = partnerCardsAdapter

        binding.searchPartnerCardsPager.registerOnPageChangeCallback(object :
                OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position == partners.size) {
                        viewModel.updatePartners()
                    }
                }
            })

        lifecycleScope.launch {
            viewModel.partners.collect {
                binding.searchPartnerCardsPager.setCurrentItem(0, true)
                partnerCardsAdapter.updateItems(it.toMutableList())
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
