package com.example.databinding

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Long,
    val avatar_url: String,
    val login: String
) : Parcelable
