package com.example.fampayassgnt.dataModels


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MainApiResponse(
    @Json(name = "card_groups")
    val cardGroups: List<CardGroup>?
)