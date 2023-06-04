package com.donxux.codate.presentation.view.matching

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.donxux.codate.databinding.FragmentPartnerCardProgressBinding

class PartnerCardProgressFragment : Fragment() {

    private var _binding: FragmentPartnerCardProgressBinding? = null

    private val binding get() = _binding!!

    companion object {

        fun newInstance(): PartnerCardProgressFragment {
            val args = Bundle()

            val fragment = PartnerCardProgressFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPartnerCardProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
