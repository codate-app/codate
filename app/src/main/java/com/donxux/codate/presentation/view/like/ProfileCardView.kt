package com.donxux.codate.presentation.view.like

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.donxux.codate.R
import com.donxux.codate.databinding.ViewProfileCardBinding
import com.google.android.material.card.MaterialCardView

class ProfileCardView(context: Context, attrs: AttributeSet) : MaterialCardView(context, attrs) {
    private val binding: ViewProfileCardBinding

    init {
        val attributes = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ProfileCardView,
            0,
            0
        )

        binding = ViewProfileCardBinding.inflate(LayoutInflater.from(context), this, true)

        try {
            val name = attributes.getString(R.styleable.ProfileCardView_name) ?: ""
            binding.likeProfileName.text = name
        } finally {
            attributes.recycle()
        }
    }
}
