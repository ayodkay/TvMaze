package com.oamaru.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class Image(
    @SerialName("medium")
    val medium: String,
    @SerialName("original")
    val original: String
) : Parcelable
