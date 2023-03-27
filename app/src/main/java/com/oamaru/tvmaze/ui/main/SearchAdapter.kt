package com.oamaru.tvmaze.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oamaru.data.models.response.SearchResponse
import com.oamaru.tvmaze.SearchListener
import com.oamaru.tvmaze.databinding.SearchItemsBinding

class SearchAdapter(var search: List<SearchResponse>, var listener: SearchListener) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(private val binding: SearchItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(search: SearchResponse, listener: SearchListener) {
            binding.showImage = search.show.image?.original
            binding.showName = search.show.name
            binding.showSummary = search.show.summary.orEmpty()

            binding.root.setOnClickListener {
                listener.onClick(search)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchItemsBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return search.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(search.getOrNull(position) ?: return, listener)
    }
}
