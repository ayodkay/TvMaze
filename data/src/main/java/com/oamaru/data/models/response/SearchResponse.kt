package com.oamaru.data.models.response

import android.os.Parcelable
import com.oamaru.data.models.Show
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class SearchResponse(
    @SerialName("score")
    val score: Double,
    @SerialName("show")
    val show: Show
) : Parcelable
