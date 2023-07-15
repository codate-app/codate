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
import com.donxux.codate.domain.model.User
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

    companion object {
        const val maxEnabledViewPagerScrollProgress = 0.1f
        const val minEnabledScrollViewScrollProgress = 0.9f
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMatchingBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[MatchingViewModel::class.java]
        binding.viewModel = viewModel

        lifecycleScope.launch {
            viewModel.currentPartner.collect { partner ->
                setDetailText(partner)
            }
        }

        val partners = viewModel.partners.value
        setViewPager(partners)
        linkToActivityMotionWithMatchingMotion()
        setOnScrollChangeListener()
        return binding.root
    }

    private fun setDetailText(partner: User) {
        binding.matchingProfileDetailName.text = partner.name
        binding.matchingProfileDetailAge.text = partner.age.toString()
        binding.matchingProfileDetailBio.text = partner.bio
    }

    private fun setViewPager(partners: List<User>) {
        partnerCardsAdapter =
            PartnerCardsAdapter(requireActivity(), partners.toMutableList())
        binding.matchingSearchPartnerCardsPager.adapter = partnerCardsAdapter

        binding.matchingSearchPartnerCardsPager.registerOnPageChangeCallback(object :
            OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == partners.size) {
                    viewModel.updatePartners()
                    viewModel.setCurrentPartner(0)
                } else {
                    viewModel.setCurrentPartner(position)
                }
            }
        })

        lifecycleScope.launch {
            viewModel.partners.collect {
                binding.matchingSearchPartnerCardsPager.setCurrentItem(0, true)
                partnerCardsAdapter.updateItems(it.toMutableList())
            }
        }
    }

    private fun setOnScrollChangeListener() {
        binding.matchingScrollView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (isGetStartToScrollUp(scrollY, oldScrollY)) {
                binding.matchingScrollView.enabledScroll = false
                (requireActivity() as MainActivity).transitionToStartRootLayout()
            }
        }
    }

    private fun isGetStartToScrollUp(scrollY: Int, oldScrollY: Int) =
        scrollY <= 0 && scrollY <= oldScrollY

    /**
     * Link MainActivity Motion Layout with Matching Motion Layout
     * using motionProgress channel of MainActivity.
     */
    private fun linkToActivityMotionWithMatchingMotion() {
        motionProgress = (requireActivity() as MainActivity).motionProgress
        lifecycleScope.launch {
            for (progress: Float in motionProgress!!) {
                binding.matchingMotionLayout.progress = progress
                binding.matchingSearchPartnerCardsPager.isUserInputEnabled =
                    progress <= minEnabledScrollViewScrollProgress
                if (progress >= maxEnabledViewPagerScrollProgress) {
                    binding.matchingScrollView.enabledScroll = true
                    binding.matchingScrollView.scrollTo(0, 1)
                    if (binding.matchingScrollView.scrollY == 0) {
                        binding.matchingScrollView.enabledScroll = false
                    }
                } else {
                    binding.matchingScrollView.enabledScroll = false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
