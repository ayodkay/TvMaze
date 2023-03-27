package com.oamaru.data.models.response

import android.os.Parcelable
import com.oamaru.data.models.Image
import com.oamaru.data.models.Rating
import com.oamaru.data.models.ScheduleLinks
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class ShowEpisodeResponse(
    @SerialName("airdate")
    val airdate: String?,
    @SerialName("airstamp")
    val airstamp: String?,
    @SerialName("airtime")
    val airtime: String?,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: Image?,
    @SerialName("_links")
    val links: ScheduleLinks?,
    @SerialName("name")
    val name: String?,
    @SerialName("number")
    val number: Int,
    @SerialName("rating")
    val rating: Rating?,
    @SerialName("runtime")
    val runtime: Int,
    @SerialName("season")
    val season: Int,
    @SerialName("summary")
    val summary: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("url")
    val url: String?
) : Parcelable {

    fun seasonEpisode() = "Season $season Episode $number"

    fun episodeSummary() = summary.orEmpty()
}
