package com.example.fampayassgnt.dataModels


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cta(
    @Json(name = "bg_color")
    val bgColor: String?,
    @Json(name = "text")
    val text: String?,
    @Json(name = "text_color")
    val textColor: String?,
    @Json(name = "url")
    val url: String?
)