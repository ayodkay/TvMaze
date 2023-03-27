package com.oamaru.tvmaze.ui.schedule

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oamaru.data.models.response.ShowScheduleResponse
import com.oamaru.tvmaze.R
import com.oamaru.tvmaze.utils.GridSpacingItemDecoration

@BindingAdapter(value = ["android:schedule", "android:listeners"])
fun RecyclerView.schedule(schedules: List<ShowScheduleResponse>?, listeners: ScheduleListeners) {
    if (schedules != null) {
        val spanCount = 2
        val spacing = resources.getDimensionPixelSize(R.dimen.dimen_10dp)
        val includeEdge = false
        addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
        if (adapter == null) {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = ScheduleAdapter(schedules, listeners)
        } else {
            (adapter as ScheduleAdapter).apply {
                this.schedules = schedules
                this.listeners = listeners

                notifyDataSetChanged()
            }
        }
    }
}
