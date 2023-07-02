package com.donxux.codate.presentation.view.custom

import android.content.Context
import android.util.AttributeSet

class Badge @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatTextView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val height = measuredHeight
        val width = measuredWidth

        // width가 height 보다 작다면 1:1 비율로 height에 맞춘다.
        if (width < height) {
            super.onMeasure(heightMeasureSpec, heightMeasureSpec)
        }
    }
}
