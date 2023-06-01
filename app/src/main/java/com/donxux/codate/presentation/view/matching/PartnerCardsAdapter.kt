package com.donxux.codate.presentation.view.matching

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.donxux.codate.databinding.ViewProgressBinding
import com.donxux.codate.databinding.ViewSearchPartnerCardBinding
import com.donxux.codate.domain.model.User
import com.donxux.codate.domain.model.userOf
import kotlin.IllegalArgumentException

class PartnerCardsAdapter(private var items: MutableList<User> = mutableListOf()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val userForProgress: User =
            userOf(name = "", age = -1, fields = listOf(), bio = "")

        private const val userViewType = 0
        private const val progressViewType = 1
    }

    init {
        this.items.add(userForProgress)
    }

    inner class PartnerCardViewHolder(val binding: ViewSearchPartnerCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.user = user
        }
    }

    inner class ProgressViewHolder(val binding: ViewProgressBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            userViewType -> {
                PartnerCardViewHolder(
                    ViewSearchPartnerCardBinding.inflate(inflater, parent, false)
                )
            }

            progressViewType -> {
                ProgressViewHolder(ViewProgressBinding.inflate(inflater, parent, false))
            }

            else -> {
                throw IllegalArgumentException("Undefined view type.")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < items.size - 1) {
            userViewType
        } else {
            progressViewType
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PartnerCardViewHolder) {
            holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(items: MutableList<User>) {
        this.items = items
        this.items.add(userForProgress)
        notifyItemRangeChanged(0, this.items.size)
    }
}
