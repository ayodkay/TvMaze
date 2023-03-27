package com.oamaru.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class Network(
    @SerialName("country")
    val country: Country?,
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("officialSite")
    val officialSite: String?
) : Parcelable
