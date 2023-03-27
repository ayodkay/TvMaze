package com.oamaru.data.models.response

import android.os.Parcelable
import com.oamaru.data.models.Image
import com.oamaru.data.models.Network
import com.oamaru.data.models.ShowLinks
import com.oamaru.data.models.WebChannel
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class ShowSeasonsResponse(
    @SerialName("endDate")
    val endDate: String,
    @SerialName("episodeOrder")
    val episodeOrder: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: Image,
    @SerialName("_links")
    val links: ShowLinks,
    @SerialName("name")
    val name: String,
    @SerialName("network")
    val network: Network,
    @SerialName("number")
    val number: Int,
    @SerialName("premiereDate")
    val premiereDate: String,
    @SerialName("summary")
    val summary: String?,
    @SerialName("url")
    val url: String,
    @SerialName("webChannel")
    val webChannel: WebChannel?
) : Parcelable
