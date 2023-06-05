package com.donxux.codate.presentation.view.matching

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.donxux.codate.databinding.FragmentMatchingBinding
import com.donxux.codate.presentation.view.MainActivity
import com.donxux.codate.presentation.viewmodel.MatchingViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class MatchingFragment : Fragment() {

    private var _binding: FragmentMatchingBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: MatchingViewModel

    private lateinit var partnerCardsAdapter: PartnerCardsAdapter

    private var motionProgress: Channel<Float>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMatchingBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MatchingViewModel::class.java]

        val partners = viewModel.partners.value
        partnerCardsAdapter =
            PartnerCardsAdapter(requireActivity(), partners.toMutableList())
        binding.searchPartnerCardsPager.adapter = partnerCardsAdapter

        setReceiveProgressOfMainActivityMotion()

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
        setRootLayoutTransitionListener()

        return binding.root
    }

    private fun setReceiveProgressOfMainActivityMotion() {
        motionProgress = (requireActivity() as MainActivity).motionProgress
        lifecycleScope.launch {
            for (progress: Float in motionProgress!!) {
                binding.matchingRootLayout.progress = progress
            }
        }
    }

    private fun setRootLayoutTransitionListener() {
        binding.matchingRootLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
            ) = Unit

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float,
            ) = Unit

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) = Unit

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float,
            ) = Unit
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
