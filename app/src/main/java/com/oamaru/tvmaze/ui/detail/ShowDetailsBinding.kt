package com.oamaru.tvmaze.ui.detail

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oamaru.tvmaze.utils.ChipAdapter

@BindingAdapter(value = ["android:chips"])
fun RecyclerView.chips(chips: List<String>?) {
    if (chips != null) {
        if (adapter == null) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ChipAdapter(chips)
        } else {
            (adapter as ChipAdapter).apply {
                this.chips = chips
                notifyDataSetChanged()
            }
        }
    }
}
