package com.oamaru.tvmaze.ui.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.oamaru.data.models.Show
import com.oamaru.data.models.response.ShowSeasonsResponse
import org.koin.core.component.KoinComponent

class ShowDetailsViewModel : ViewModel(), KoinComponent {
    val selectedShow = ObservableField<Show>()
    val selectedShowSeasons = ObservableField<List<ShowSeasonsResponse>>()
}
