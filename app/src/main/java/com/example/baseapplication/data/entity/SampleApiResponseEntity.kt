package com.example.baseapplication.data.entity

import com.squareup.moshi.Json

data class SampleApiResponseEntity(
    @Json(name = "results") val sampleChildResponseEntity: List<SampleApiResponseChildEntity>
)

data class SampleApiResponseChildEntity(
    @Json(name = "name") val name: String,
    @Json(name = "url") val image: String
)