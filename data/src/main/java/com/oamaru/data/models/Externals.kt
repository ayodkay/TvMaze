package com.oamaru.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class Externals(
    @SerialName("imdb")
    val imdb: String?,
    @SerialName("thetvdb")
    val thetvdb: Int?,
    @SerialName("tvrage")
    val tvrage: Int?
) : Parcelable
