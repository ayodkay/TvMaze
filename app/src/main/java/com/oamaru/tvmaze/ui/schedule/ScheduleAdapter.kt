package com.oamaru.tvmaze.ui.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oamaru.data.models.response.ShowScheduleResponse
import com.oamaru.tvmaze.databinding.ScheduleItemsBinding
import com.oamaru.tvmaze.ifNull

class ScheduleAdapter(var schedules: List<ShowScheduleResponse>, var listeners: ScheduleListeners) :
    RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    class ScheduleViewHolder(private val binding: ScheduleItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(schedules: ShowScheduleResponse, listeners: ScheduleListeners) {
            binding.episodeName = schedules.name
            binding.showImage = schedules.show.image?.medium
            binding.showName = schedules.show.name
            binding.showRating = schedules.show.rating?.average.ifNull { 0.0 }.toString()

            binding.root.setOnClickListener {
                listeners.onClick(schedules)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ScheduleItemsBinding.inflate(inflater, parent, false)
        return ScheduleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return schedules.size
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(schedules.getOrNull(position) ?: return, listeners)
    }
}
