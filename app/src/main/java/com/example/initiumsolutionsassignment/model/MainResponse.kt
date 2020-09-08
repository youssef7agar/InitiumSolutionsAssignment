package com.example.initiumsolutionsassignment.model

import com.squareup.moshi.Json
import java.util.*

data class MainResponse(
    @Json(name = "Collection")
    val collection: Array<Entity>,
    @Json(name = "Err_Flag")
    val errFlag: Boolean,
    @Json(name = "Err_Desc")
    val errDesc: String
){
    data class Entity(
        @Json (name= "EntityEnglishName")
        val entityEnglishName: String,
        @Json (name= "EntityArabicName")
        val entityArabicName: String,
        @Json (name= "EntityLogo")
        val entityLogo: Base64,
        @Json (name= "EntityID")
        val entityID: String
    )
}