package com.donxux.codate.presentation.view.matching

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.donxux.codate.domain.model.User
import com.donxux.codate.domain.model.userOf

class PartnerCardsAdapter(
    activity: FragmentActivity,
    private var items: MutableList<User> = mutableListOf()
) : FragmentStateAdapter(activity) {

    companion object {
        private val userForProgress: User =
            userOf(name = "", age = -1, fields = listOf(), codeUrl = "", bio = "")

        private const val userViewType = 0
        private const val progressViewType = 1
    }

    init {
        this.items.add(userForProgress)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < items.size - 1) {
            userViewType
        } else {
            progressViewType
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(items: MutableList<User>) {
        this.items = items
        this.items.add(userForProgress)
        notifyItemRangeChanged(0, this.items.size)
    }

    override fun createFragment(position: Int): Fragment {
        return if (position < items.size - 1) {
            PartnerCardFragment.newInstance(position)
        } else {
            PartnerCardProgressFragment.newInstance()
        }
    }
}
