package com.oamaru.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class ShowLinks(
    @SerialName("href")
    val href: String?
) : Parcelable
