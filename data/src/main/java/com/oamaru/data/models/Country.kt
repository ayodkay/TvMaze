package com.oamaru.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class Country(
    @SerialName("code")
    val code: String,
    @SerialName("name")
    val name: String,
    @SerialName("timezone")
    val timezone: String
) : Parcelable
