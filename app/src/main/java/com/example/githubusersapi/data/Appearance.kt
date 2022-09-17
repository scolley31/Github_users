package com.example.githubusersapi.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Appearance (

    val mainTitle: String?,
    val subTitle: String?,
    val thumbnail: String?,
    val subscript: String?

    ): Parcelable {

}