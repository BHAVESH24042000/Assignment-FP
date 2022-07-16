package com.example.fampayassgnt.dataModels


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FormattedTitle(
    @Json(name = "align")
    val align: String?,
    @Json(name = "entities")
    val entities: List<EntityX>?,
    @Json(name = "text")
    val text: String?
)