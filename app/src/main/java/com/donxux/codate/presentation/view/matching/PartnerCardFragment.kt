package com.donxux.codate.presentation.view.matching

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.donxux.codate.databinding.FragmentPartnerCardBinding
import com.donxux.codate.domain.model.User
import com.donxux.codate.presentation.viewmodel.MatchingViewModel
import com.donxux.codate.utils.GistsHtmlUtils

class PartnerCardFragment : Fragment() {
    private var _binding: FragmentPartnerCardBinding? = null

    private val binding get() = _binding!!

    private var webView: WebView? = null
    private val webViewClient: WebViewClient = WebViewClient()
    private lateinit var matchingViewModel: MatchingViewModel
    private lateinit var partner: User

    companion object {
        const val partnerIndexKey = "partnerIndex"
        fun newInstance(partnerIndex: Int): PartnerCardFragment {
            val args = Bundle()
            args.putInt(partnerIndexKey, partnerIndex)

            val fragment = PartnerCardFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPartnerCardBinding.inflate(inflater, container, false)
        matchingViewModel = ViewModelProvider(requireActivity())[MatchingViewModel::class.java]

        arguments?.getInt(partnerIndexKey)?.let {
            binding.user = matchingViewModel.partners.value[it]
            partner = matchingViewModel.partners.value[it]
        }

        setWebView()
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        webView = binding.viewCode.viewCodeWebView
        binding.viewCode.viewCodeWebView.webViewClient = webViewClient
        webView?.settings?.javaScriptEnabled = true
        webView?.loadDataWithBaseURL(
            null,
            GistsHtmlUtils.getHtmlCode(scriptCode = partner.codeUrl),
            "text/html",
            "UTF-8",
            null
        )
    }

    private fun destroyWebView() {
        webView?.clearHistory()
        webView?.clearCache(true)
        webView?.destroy()
        webView = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        destroyWebView()
        _binding = null
    }
}
