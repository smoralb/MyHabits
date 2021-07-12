package com.example.baseapplication.data.entity

import com.squareup.moshi.Json

data class SampleApiResponseEntity(
    @Json(name = "id") val whatEverID: String
)