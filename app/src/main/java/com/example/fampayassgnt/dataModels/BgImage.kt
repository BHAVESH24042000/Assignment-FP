package com.example.fampayassgnt.dataModels


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BgImage(
    @Json(name = "aspect_ratio")
    val aspectRatio: Double?,
    @Json(name = "image_type")
    val imageType: String?,
    @Json(name = "image_url")
    val imageUrl: String?
)