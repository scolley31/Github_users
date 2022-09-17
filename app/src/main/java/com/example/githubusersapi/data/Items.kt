package com.example.githubusersapi.data

import android.os.Parcelable
import com.example.githubusersapi.overview.DataItem
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Items (

    val items: List<Item>?

): Parcelable {

}