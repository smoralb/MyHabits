package com.example.baseapplication.domain.model

data class SampleDataModel(
    val results: List<SampleChildModel>
)

data class SampleChildModel(
    val bookDetails: List<SampleChildDetailsModel>
)

data class SampleChildDetailsModel(
    val title: String,
    val description: String,
    val publisher: String
)