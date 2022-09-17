package com.example.githubusersapi.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item (

    @Json(name = "type") val type: String?,
    val title: String?,
    val ref: String?,
    @Json(name = "appearance") val appearance: Appearance?,
    val extra: Extra?

): Parcelable {

}
