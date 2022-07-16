package com.example.fampayassgnt.dataModels


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Icon(
    @Json(name = "aspect_ratio")
    val aspectRatio: Int?,
    @Json(name = "image_type")
    val imageType: String?,
    @Json(name = "image_url")
    val imageUrl: String?
)