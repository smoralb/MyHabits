package com.example.baseapplication.presentation.main.firstView.adapter

sealed class SampleDataItems(open val name: String) {
    data class SampleDataItem(override val name: String, val url: String): SampleDataItems(name)
}