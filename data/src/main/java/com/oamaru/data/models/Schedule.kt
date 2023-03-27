package com.oamaru.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class Schedule(
    @SerialName("days")
    val days: List<String>,
    @SerialName("time")
    val time: String
) : Parcelable
