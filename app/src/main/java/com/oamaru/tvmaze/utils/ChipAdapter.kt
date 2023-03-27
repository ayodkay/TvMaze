package com.oamaru.tvmaze.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oamaru.tvmaze.databinding.ChipsItemsBinding

class ChipAdapter(var chips: List<String>) :
    RecyclerView.Adapter<ChipAdapter.ChipViewHolder>() {

    class ChipViewHolder(private val binding: ChipsItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chip: String) {
            binding.chip = chip
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChipsItemsBinding.inflate(inflater, parent, false)
        return ChipViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return chips.size
    }

    override fun onBindViewHolder(holder: ChipViewHolder, position: Int) {
        holder.bind(chips.getOrNull(position) ?: return)
    }
}
