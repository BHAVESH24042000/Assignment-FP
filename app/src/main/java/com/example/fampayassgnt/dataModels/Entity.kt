package com.example.fampayassgnt.dataModels


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Entity(
    @Json(name = "color")
    val color: String?,
    @Json(name = "text")
    val text: String?
)