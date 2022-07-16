package com.example.fampayassgnt.dataModels


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardGroup(
    @Json(name = "cards")
    val cards: List<Card>?,
    @Json(name = "design_type")
    val designType: String?,
    @Json(name = "height")
    val height: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "is_scrollable")
    val isScrollable: Boolean?,
    @Json(name = "name")
    val name: String?
)