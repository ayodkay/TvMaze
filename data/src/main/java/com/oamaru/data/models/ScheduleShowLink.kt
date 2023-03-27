package com.oamaru.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class ScheduleShowLink(
    @SerialName("nextepisode")
    val nextEpisode: NextEpisode,
    @SerialName("previousepisode")
    val previousEpisode: PreviousEpisode,
    @SerialName("self")
    val self: Self
) : Parcelable
