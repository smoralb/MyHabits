package com.example.baseapplication.data

import com.example.baseapplication.data.entity.SampleApiResponseEntity
import retrofit2.Response
import retrofit2.http.GET

interface SampleApi {

    @GET("/svc/books/v3/lists.json?list=hardcover-fiction")
    suspend fun getSampleData(): Response<SampleApiResponseEntity>

}