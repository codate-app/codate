package com.donxux.codate.presentation.view.explore

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.donxux.codate.R
import com.donxux.codate.databinding.ViewExploreCardBinding
import com.google.android.material.card.MaterialCardView

class ExploreCardView(context: Context, attrs: AttributeSet) : MaterialCardView(context, attrs) {
    private val binding: ViewExploreCardBinding

    init {
        val attributes = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ExploreCardView,
            0,
            0
        )

        binding = ViewExploreCardBinding.inflate(LayoutInflater.from(context), this, true)

        try {
            val title = attributes.getString(R.styleable.ExploreCardView_title) ?: ""
            val background = attributes.getResourceId(R.styleable.ExploreCardView_background, -1)
            binding.exploreCardText.text = title
            binding.exploreBackgroundLayout.background =
                ContextCompat.getDrawable(context, background)
        } finally {
            attributes.recycle()
        }
    }
}
