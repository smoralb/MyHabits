package com.example.baseapplication.data

import com.example.baseapplication.data.entity.SampleApiResponseEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val POKEMON_LIMIT = 150

interface SampleApi {

    @GET ("/api/v2/pokemon/")
    suspend fun getSampleData(@Query("limit") limit: Int = POKEMON_LIMIT ): Response<SampleApiResponseEntity>

}