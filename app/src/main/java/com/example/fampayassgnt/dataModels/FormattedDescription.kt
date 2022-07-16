package com.example.fampayassgnt.dataModels


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FormattedDescription(
    @Json(name = "entities")
    val entities: List<Entity>?,
    @Json(name = "text")
    val text: String?
)