package com.oamaru.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class ScheduleLinks(
    @SerialName("self")
    val self: Self,
    @SerialName("show")
    val show: Show
) : Parcelable
