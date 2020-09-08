package com.example.initiumsolutionsassignment.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Entity(
    var name: String,
    var image: Bitmap?
): Parcelable