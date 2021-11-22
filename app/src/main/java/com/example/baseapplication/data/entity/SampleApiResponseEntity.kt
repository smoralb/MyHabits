package com.example.baseapplication.data.entity

import com.squareup.moshi.Json

data class SampleApiResponseEntity(
    @Json(name = "results") val sampleChildResponseEntity: List<SampleApiResponseChildEntity>?
)

data class SampleApiResponseChildEntity(
    @Json(name = "book_details") val bookDetails: List<SampleApiChildDetailsEntity>?
)

data class SampleApiChildDetailsEntity(
    @Json(name = "title") val title: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "publisher") val publisher: String?,
    @Json(name = "primary_isbn13") val isbn: String?
)