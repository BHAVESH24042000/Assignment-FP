package com.example.fampayassgnt.dataModels


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Card(
    @Json(name = "bg_color")
    val bgColor: String?,
    @Json(name = "bg_image")
    val bgImage: BgImage?,
    @Json(name = "cta")
    val cta: List<Cta>?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "formatted_description")
    val formattedDescription: FormattedDescription?,
    @Json(name = "formatted_title")
    val formattedTitle: FormattedTitle?,
    @Json(name = "icon")
    val icon: Icon?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "url")
    val url: String?
)