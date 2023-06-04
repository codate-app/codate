package com.donxux.codate.presentation.view.matching

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.donxux.codate.databinding.FragmentPartnerCardBinding
import com.donxux.codate.domain.model.User
import com.donxux.codate.utils.GistsHtmlUtils

class PartnerCardFragment(private val partner: User) : Fragment() {
    private var _binding: FragmentPartnerCardBinding? = null

    private val binding get() = _binding!!

    private var webView: WebView? = null
    private val webViewClient: WebViewClient = WebViewClient()

    companion object {
        fun newInstance(partner: User): PartnerCardFragment {
            val args = Bundle()

            val fragment = PartnerCardFragment(partner)
            fragment.arguments = args
            return fragment
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPartnerCardBinding.inflate(inflater, container, false)
        binding.user = partner
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
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        webView?.clearHistory()
        webView?.clearCache(true)
        webView?.destroy()
        webView = null
        _binding = null
    }
}
