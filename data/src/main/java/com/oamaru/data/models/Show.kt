package com.oamaru.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class Show(
    @SerialName("averageRuntime")
    val averageRuntime: Int?,
    @SerialName("dvdCountry")
    val dvdCountry: DvdCountry?,
    @SerialName("ended")
    val ended: String?,
    @SerialName("externals")
    val externals: Externals,
    @SerialName("genres")
    val genres: List<String>,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: Image?,
    @SerialName("language")
    val language: String,
    @SerialName("_links")
    val links: ScheduleShowLink?,
    @SerialName("name")
    val name: String,
    @SerialName("network")
    val network: Network?,
    @SerialName("officialSite")
    val officialSite: String?,
    @SerialName("premiered")
    val premiered: String,
    @SerialName("rating")
    val rating: Rating?,
    @SerialName("runtime")
    val runtime: Int?,
    @SerialName("schedule")
    val schedule: Schedule,
    @SerialName("status")
    val status: String?,
    @SerialName("summary")
    val summary: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("updated")
    val updated: Int?,
    @SerialName("url")
    val url: String?,
    @SerialName("webChannel")
    val webChannel: WebChannel?,
    @SerialName("weight")
    val weight: Int?
) : Parcelable
