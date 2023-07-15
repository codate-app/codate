package com.donxux.codate.presentation.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView
import com.donxux.codate.R

// TODO : Implement Custom ScrollView
class ToggleableScrollView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ScrollView(context, attrs) {
    var enabledScroll: Boolean = false

    init {
        val attributes = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ToggleableScrollView,
            0,
            0
        )

        enabledScroll =
            attributes.getBoolean(R.styleable.ToggleableScrollView_scroll_enabled, false)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return when (ev?.action) {
            MotionEvent.ACTION_DOWN ->
                if (enabledScroll) super.onTouchEvent(ev) else enabledScroll

            else -> super.onTouchEvent(ev)
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (!enabledScroll) return false
        return super.onInterceptTouchEvent(ev)
    }
}
