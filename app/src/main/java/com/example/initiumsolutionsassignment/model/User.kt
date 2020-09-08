package com.example.initiumsolutionsassignment.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val customerId: String? = null,
    val customerFirstName: String = "",
    val customerLastName: String = ""
): Parcelable