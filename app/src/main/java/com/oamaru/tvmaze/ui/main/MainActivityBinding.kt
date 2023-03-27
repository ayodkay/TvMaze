package com.oamaru.tvmaze.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oamaru.data.models.response.SearchResponse
import com.oamaru.tvmaze.SearchListener

@BindingAdapter(value = ["android:search", "android:listener"])
fun RecyclerView.search(search: List<SearchResponse>?, listener: SearchListener) {
    if (search != null) {
        if (adapter == null) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = SearchAdapter(search, listener)
        } else {
            (adapter as SearchAdapter).apply {
                this.search = search
                this.listener = listener
                notifyDataSetChanged()
            }
        }
    }
}
