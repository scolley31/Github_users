package com.example.githubusersapi.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsDate (

    @Json(name = "getVector") val getVector: Items?

): Parcelable {

}